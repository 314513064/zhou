package ti12;
/*
 * 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果
 * 一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 */
public class Main1 {
	public static void main(String[] args) {
		char[][] matrix = 
			{
				{'a', 'b', 't', 'g'},
				{'c', 'f', 'c', 's'},
				{'j', 'd', 'e', 'h'}
			};
		String target = "acjd";
		System.out.println(solve(matrix,target));
	}
	
	public static boolean solve(char[][] matrix, String target) {
		boolean[][] flag = new boolean[matrix.length][matrix[0].length];
		for(int i = 0;i < matrix.length;i++) {
			for(int j = 0;j < matrix[0].length;j++) {
				if(solveCore(matrix, i, j, flag, target, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean solveCore(char[][] matrix,int row,int column,boolean[][] flag,String target,int index) {
		if(index == target.length())
			return true;
		boolean f = false;
		if(!flag[row][column] && matrix[row][column] == target.charAt(index)) {
			flag[row][column] = true;
			if(row > 0)
				f = solveCore(matrix, row-1, column, flag, target, index+1);
			if(!f && row < matrix.length-1)
				f = solveCore(matrix, row+1, column, flag, target, index+1);
			if(!f && column > 0)
				f = solveCore(matrix, row, column-1, flag, target, index+1);
			if(!f && column < matrix[0].length-1)
				f = solveCore(matrix, row, column+1, flag, target, index+1);
			flag[row][column] = false;
		}
		return f;
	}
	
}
