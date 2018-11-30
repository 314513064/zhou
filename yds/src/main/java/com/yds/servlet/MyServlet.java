package com.yds.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import com.yds.domain.Merge;
import com.yds.domain.Position;
import com.yds.domain.Route_Distance;

@ServerEndpoint("/websocket/{fromid}")
public class MyServlet extends HttpServlet{

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/navigation";
	private static final String USER = "root";
	private static final String PASSWORD = "1111";
	private static Connection conn = null;

	private static Map<String,MyServlet> maps = new HashMap<String, MyServlet>();
	private Session session;
	
	
	@javax.websocket.OnOpen
	public void OnOpen(Session session,@PathParam("fromid") String fromid) {
		System.out.println("OnOpen fromid:"+fromid);
		this.session = session;
		maps.put(fromid, this);
	}
	
	@javax.websocket.OnClose
	public void OnClose(@PathParam("fromid") String fromid) throws SQLException {
		if(fromid.equals("2000"))
			conn.close();
		System.out.println("OnClose");
	}
	
	@javax.websocket.OnMessage
	public void OnMessage(String msg,@PathParam("fromid") String fromid) throws IOException, EncodeException {
		String msg1 = msg.substring(msg.length()-8,msg.length());
		System.out.println("Message is "+msg1);
		List<Integer> goods_ids = new ArrayList<Integer>();
		List<Integer> rfid_ids = new ArrayList<Integer>();
		List<Position> positions = new ArrayList<Position>();
		double direction = 0;
		String toid = null;
		
		try {
			//连接数据库  
			if(conn==null) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				System.out.println("数据库连接完成");
			}
			
			//  查询硬件对应的购物车编号
			Statement stmt = conn.createStatement();
			String sql = "SELECT trolley_id FROM trolley_mcu WHERE mcu_id="+fromid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				toid = rs.getString("trolley_id");
			}
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("trolley_id is "+toid);
		
		for(String key:maps.keySet()) {
			if(key.equals(toid)) {
				try {
					Statement stmt1 = conn.createStatement();
					//查询购物车中的goods_id
					String sql1 = "SELECT goods_id FROM trolley WHERE trolley_id="+toid;
					ResultSet rs1 = stmt1.executeQuery(sql1);
					while(rs1.next()) {
						goods_ids.add(rs1.getInt("goods_id"));
					}
					//查询goods_id对应的rfid_id
					for(int ii:goods_ids) {
						sql1 = "SELECT rfid_id FROM goods WHERE id="+ii;
						rs1 = stmt1.executeQuery(sql1);
						if(rs1.next()) {
							rfid_ids.add(rs1.getInt("rfid_id"));
						}
					}
					
					//查询所处位置的rfid_id
					int begin_id = 9;
					sql1 = "SELECT id FROM rfid_tag WHERE card_number='"+msg1+"'";
					rs1 = stmt1.executeQuery(sql1);
					if(rs1.next()) {
						begin_id = rs1.getInt("id");
					}
					
					//一旦走到一个商品处，视为已经拿到该商品，删除数据
					if(rfid_ids.contains(begin_id)) {
						sql1 = "SELECT id FROM goods WHERE rfid_id="+begin_id;
						rs1 = stmt1.executeQuery(sql1);
						int del_id = 999;
						if(rs1.next()) {
							del_id = rs1.getInt("id");
						}
						sql1 = "DELETE FROM trolley WHERE trolley_id="+toid+" AND goods_id="+del_id;
						System.out.println(sql1);
						int d = stmt1.executeUpdate(sql1);
						rfid_ids.remove((Integer)begin_id);
					}
					
					if(rfid_ids.size()>0)
						positions = get_route(begin_id,rfid_ids);
					else{
						sql1 = "SELECT position_x,position_y FROM rfid_tag WHERE card_number='"+msg1+"'";
						rs1 = stmt1.executeQuery(sql1);
						if(rs1.next()) {
							positions.add(new Position(rs1.getInt("position_x"),rs1.getInt("position_y"),0));
						}
					}
					
					stmt1.close();
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(positions.size()>1) {
					Position now = positions.get(0);
					Position next = positions.get(1);
					int now_y = now.getY();
					int now_x = now.getX();
					int next_y = next.getY();
					int next_x = next.getX();
					System.out.println("now_y is "+now_y);
					System.out.println("now_x is "+now_x);
					System.out.println("next_y is "+next_y);
					System.out.println("next_x is "+next_x);
					if(now_x == next_x) {
						if(now_y >= next_y)
							direction = 0;
						else
							direction = 180;
					}else {
						double abs = Math.abs(1.0*(next_y-now_y)/(next_x-now_x));
						System.out.println("abs is "+abs);
						if(next_y <= now_y) {
							direction = 90-Math.toDegrees(Math.atan(abs));
						}else
							direction = 90+Math.toDegrees(Math.atan(abs));
						if(next_x < now_x)
							direction = -direction;
					}
				}else
					direction = 0;
				System.out.println("direction is "+direction);
				Session toSession = maps.get(toid).session;
				positions.set(0, new Position(positions.get(0)));//把所处位置的商品选择去掉
				Merge merge = new Merge();
				merge.setCode("1000");
				merge.setPositions(positions);
				merge.setDirection(direction);
				Gson gg = new Gson();
				toSession.getBasicRemote().sendText(gg.toJson(merge));
				return;
			}
		}
	    //session.getBasicRemote().sendText(toid+" is unconnected!");
	}
	
	//通过所在位置tag id :begin_id
	//和想买的商品tag id :rfid_ids
	//求出路径信息
	public static List<Position> get_route(int begin_id,List<Integer> rfid_ids) throws SQLException{
		int min = 9999;
		String min_Route = "";
		for(int i:rfid_ids) {
			Route_Distance temp = longest(i,rfid_ids,begin_id,0,"");
			if(temp.getDistance()<min) {
				min = temp.getDistance();
				min_Route = temp.getRoute();
			}
		}
		System.out.println(min);
		System.out.println(min_Route);
		String[] nodes = min_Route.split("\\+");
		List<Position> positions = new ArrayList<Position>();
		int node_count = nodes.length-1;
		Statement stmttt = conn.createStatement();
		String sqlll = "";
		ResultSet rssss = null;
		while(node_count > 0) {
			sqlll = "SELECT position_x,position_y FROM rfid_tag WHERE id="+nodes[node_count];
			rssss = stmttt.executeQuery(sqlll);
			if(rssss.next()) {
				positions.add(new Position(rssss.getInt("position_x"),rssss.getInt("position_y"),1));
			}
			sqlll = "SELECT next_id FROM route WHERE begin_id="+nodes[node_count]+" AND end_id="+nodes[node_count-1];
			rssss = stmttt.executeQuery(sqlll);
			int next_id;
			if(rssss.next()) {
				next_id = rssss.getInt("next_id");
				if(next_id == Integer.parseInt(nodes[node_count-1])) {
					
				}else {
					sqlll = "SELECT position_x,position_y FROM rfid_tag WHERE id="+next_id;
					rssss = stmttt.executeQuery(sqlll);
					if(rssss.next()) {
						positions.add(new Position(rssss.getInt("position_x"),rssss.getInt("position_y"),0));
					}
				}
			}
			node_count--;
		}
		sqlll = "SELECT position_x,position_y FROM rfid_tag WHERE id="+nodes[0];
		rssss = stmttt.executeQuery(sqlll);
		if(rssss.next()) {
			positions.add(new Position(rssss.getInt("position_x"),rssss.getInt("position_y"),1));
		}
		return positions;
	}
	
	public static Route_Distance longest(int id,List<Integer> ids,int begin_id,int sum,String route) throws SQLException {
		
		Statement stmtt = conn.createStatement();
		if(ids.size()==1) {
			String sqll = "SELECT distance FROM route WHERE end_id="+begin_id+" AND begin_id="+id;
			ResultSet rss = stmtt.executeQuery(sqll);
			int min = 0;
			if(rss.next()) {
				min = rss.getInt("distance");
			}
			
			Route_Distance rd = new Route_Distance();
			rd.setDistance(sum+min);
			rd.setRoute(route+id+"+"+begin_id);
			return rd;
		}
			
		List<Integer> copy = new ArrayList<Integer>();
		String routee = id+"+";
		int min = 9999;
		int min_id = 999;
		int sizee = ids.size();
		
		for(int zz:ids) {
			if(zz!=id) {
				copy.add(zz);
				String sqll = "SELECT distance FROM route WHERE end_id="+zz+" AND begin_id="+id;
				ResultSet rss = stmtt.executeQuery(sqll);
				if(rss.next()) {
					int temp = rss.getInt("distance");
					if(temp < min) {
						min = temp;
						min_id = zz;
					}
				}
			}
		}
		return longest(min_id,copy,begin_id,sum+min,route+routee);
	}
}
