package ti47;
/*
 * 礼物的最大价值
 * 在一个m*n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或向下移动一格，直到到达
 * 棋盘右下角。给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物。
 */
public class Main1 {
	public static void main(String[] args) {
		int[][] matrix = {
				{ 1 ,10,3 ,8 },
				{ 12,2 ,9 ,6 },
				{ 5 ,7 ,4 ,11},
				{ 3 ,7 ,16,5 }
		};
		int[][] dp = new int[matrix.length][matrix[0].length];
		dp[0][0] = matrix[0][0];
		for(int i = 1;i < matrix.length;i++) {
			dp[i][0] += dp[i-1][0] + matrix[i][0];
		}
		for(int i = 1;i < matrix[0].length;i++) {
			dp[0][i] = dp[0][i-1] + matrix[i][0];
		}
		
		for(int i = 1;i < matrix.length;i++) {
			for(int j = 1;j < matrix[0].length;j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
			}
		}
		System.out.println(dp[dp.length-1][dp[0].length-1]);
	}
}
