package ti51;
/*
 * 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对
 * 输入一个数组，求出这个数组中的逆序对的总数。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {7,5,6,4};
		int[] dp = new int[arr.length];
		for(int i = 1;i < arr.length;i++) {
			for(int j = i-1;j >= 0;j--) {
				if(arr[j] > arr[i]) {
					dp[i]++;
				}
			}
			dp[i] += dp[i-1];
		}
		System.out.println(dp[dp.length-1]);
	}
}
