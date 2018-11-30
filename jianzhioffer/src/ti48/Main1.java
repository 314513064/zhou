package ti48;
/*
 * 最长不含重复字符串的子字符串
 * 请从字符串中找出一个最长的不包含重复字符串的子字符串，计算该最长子字符串的长度。
 */
public class Main1 {
	public static void main(String[] args) {
		String str = "arabcacfr";
		int[] dp = new int[str.length()];
		dp[0] = 1;
		int maxLen = 0;
		for(int i = 1;i < str.length();i++) {
			int j = i - 1;
			for(;j >= i-dp[i-1];j --) {
				if(str.charAt(i) == str.charAt(j)) {
					break;
				}
			}
			if(j == i-dp[i-1]-1) {
				dp[i] = dp[i-1]+1;
			}else {
				dp[i] = i-j;
			}
			if(maxLen < dp[i])
				maxLen = dp[i];
		}
		System.out.println(maxLen);
	}
}
