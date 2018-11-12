package ti13;
/*
 * 机器人的运动范围
 * 地上有一个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动，
 * 它每次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和
 * 大于k的格子。例如，k=18，机器人能够进入方格(35,37)，因为3+5+3+7=18。
 * 但不可以进入(35,38)的格子。请问该机器人能够到达多少个格子。
 */
public class Main1 {
	public static void main(String[] args) {
		int k = 2;
		int m = 5;
		int n = 5;
		System.out.println(solve(k, m, n));
	}
	
	public static int solve(int k,int m,int n) {
		boolean[][] flag = new boolean[m][n];
		return solveCore(k, m, n, flag, 0, 0);
	}
	
	public static int solveCore(int k,int m,int n,boolean[][] flag,int row,int column) {
		if(row < 0 || row >= m || column < 0 || column >= n || flag[row][column] || !checkDigital(row, column, k))
			return 0;
		flag[row][column] = true;
		return 1 + solveCore(k, m, n, flag, row+1, column)
				 + solveCore(k, m, n, flag, row-1, column)
				 + solveCore(k, m, n, flag, row, column+1)
				 + solveCore(k, m, n, flag, row, column-1);
	}
	public static boolean checkDigital(int row,int column,int k) {
		int add = 0;
		while(row > 0) {
			add += row%10;
			row /= 10;
		}
		while(column > 0) {
			add += column%10;
			column /= 10;
		}
		
		return (k >= add);
	}
}
