package ti42;
/*
 * 连续子数组的最大和
 * 输入一个整形数组，数组里有正有负。数组中的一个或多个整数组成一个子数组。
 * 求所有子数组的最大和。时间复杂度为O(n)
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {1,-2,3,10,-4,7,2,-5};
		int[] dp = new int[arr.length];
		dp[0] = arr[0];
		int max = 0;
		for(int i = 1;i < arr.length;i++) {
			if(dp[i-1] <= 0)
				dp[i] = arr[i];
			else
				dp[i] = arr[i] + dp[i-1];
			if(max < dp[i])
				max = dp[i];
		}
		System.out.println(max);
	}
}
