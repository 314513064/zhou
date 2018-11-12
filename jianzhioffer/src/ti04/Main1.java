package ti04;
/*
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入
 * 一个二维数组和一个目标数，判断二维数组中是否存在该数。
 */
public class Main1 {
	public static void main(String[] args) {
		int[][] arr = 
			{
				{ 1 ,2 ,8 ,9 },
				{ 2 ,4 ,9 ,12},
				{ 4 ,7 ,10,13},
				{ 6 ,8 ,11,15}
			};
		System.out.println(solve(arr, 16));
	}
	public static boolean solve(int[][] arr, int target) {
		int x = 0;
		int y = arr[0].length-1;
		while(x < arr.length && y >= 0) {
			if(arr[x][y] == target)
				return true;
			else if(arr[x][y] < target)
				x++;
			else
				y--;
		}
		return false;
	}
}
