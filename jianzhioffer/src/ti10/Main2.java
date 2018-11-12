package ti10;
/*
 * 青蛙跳台阶问题
 * 一只青蛙可以跳上1级台阶，也可以跳上2级台阶。求该青蛙
 * 上一个n级台阶总共有多少种跳法
 */
public class Main2 {
	public static void main(String[] args) {
		int n = 8;
		System.out.println(solve(n));
	}
	public static int solve(int n) {
		if(n == 1)
			return 1;
		if( n == 2)
			return 2;
		int[] dp = new int[n+1];
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3;i < n+1;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}
}
