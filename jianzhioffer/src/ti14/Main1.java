package ti14;
/*
 * 剪绳子
 * 给你一根长度为n的绳子，请把绳子剪成m段(n>1,m>1),每段绳子的长度记为k[0],k[1],k[2]......
 * 请问k[0]*k[1]*......*k[m]可能的最大乘积是多少？例如绳子长度为8，剪成2、3、3三段，最大乘积18
 */
public class Main1 {
	public static void main(String[] args) {
		int n = 8;
		System.out.println(solve(n));
	}
	
	public static int solve(int n) {
		if(n < 2)
			return 0;
		if(n == 2)
			return 1;
		if(n == 3)
			return 2;
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		for(int i = 4;i <= n;i++) {
			int max = 0;
			for(int j = 1;j <= i/2;j++) {
				int tmp = dp[j] * dp[i-j];
				if(max < tmp)
					max = tmp;
			}
			dp[i] = max;
		}
		return dp[n];
	}
}
