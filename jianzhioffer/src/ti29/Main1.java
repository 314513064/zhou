package ti29;
/*
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class Main1 {
	public static void main(String[] args) {
		int[][] matrix = {
				{1 ,2 ,3 ,4 },
				{5 ,6 ,7 ,8 },
				{9 ,10,11,12},
				{13,14,15,16}
		};
		solve(matrix);
	}
	
	public static void solve(int[][] matrix) {
		int row = matrix.length;
		int column = matrix[0].length;
		int start = 0;
		while(row > start*2 && column > start*2) {
			int endRow = row - start;
			int endColumn = column - start;
			for(int i = start;i < endColumn;i++) {
				System.out.print(matrix[start][i]+" ");
			}
			for(int i = start+1;i < endRow;i++) {
				System.out.print(matrix[i][endColumn-1]+" ");
			}
			for(int i = endColumn-2;i >= start;i--) {
				System.out.print(matrix[endRow-1][i]+" ");
			}
			for(int i = endRow-2;i > start;i--) {
				System.out.print(matrix[i][start]+" ");
			}
			start++;
		}
	}
}
