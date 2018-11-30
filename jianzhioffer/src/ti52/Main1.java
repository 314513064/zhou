package ti52;
/*
 * 两个链表中的第一个公共节点
 * 输入两个链表，找出它们的第一个公共节点。
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(2);
		head1.next.next = new ListNode(3);
		head1.next.next.next = new ListNode(6);
		head1.next.next.next.next = new ListNode(7);
		
		ListNode head2 = new ListNode(4);
		head2.next = new ListNode(5);
		head2.next.next = head1.next.next.next;
		
		solve(head1,head2);
	}
	
	public static void solve(ListNode head1 , ListNode head2) {
		ListNode node1 = head1;
		ListNode node2 = head2;
		int count1 = 0;
		int count2 = 0;
		while(node1 != null) {
			count1++;
			node1 = node1.next;
		}
		while(node2 != null) {
			count2++;
			node2 = node2.next;
		}
		int diff = Math.abs(count1-count2);
		node1 = head1;
		node2 = head2;
		if(count1 >= count2) {
			while(diff > 0) {
				node1 = node1.next;
				diff--;
			}
		}else {
			while(diff > 0) {
				node2 = node2.next;
				diff--;
			}
		}
		while(node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
		}
		System.out.println(node1.val);
	}
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
}