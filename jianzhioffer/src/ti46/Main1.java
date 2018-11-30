package ti46;
/*
 * 把数字翻译成字符串
 * 给定一个数字，按照如下规则把它翻译成字符串：
 * 0翻译成"a"，1翻译成"b"，......，11翻译成"l",25翻译成"z"。
 * 一个数字可能有多种翻译。例如12258有5种不同翻译。
 * 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class Main1 {
	public static void  main(String[] args) {
		String num = "12258";
		if(num.length() == 1) {
			System.out.println(1);
			return;
		}
		if(num.length() == 2) {
			if(Integer.parseInt(num) < 26)
				System.out.println(2);
			else
				System.out.println(1);
			return;
		}
			
		int[] dp = new int[num.length()];
		dp[0] = 1;
		if(Integer.parseInt(num.substring(0, 2)) < 26)
			dp[1] = 2;
		else
			dp[1] = 1;
		for(int i = 2;i < dp.length;i++) {
			dp[i] = dp[i-1];
			if(Integer.parseInt(num.substring(i-1,i+1)) < 26) {
				dp[i] += dp[i-2];
			}
		}
		System.out.println(dp[dp.length-1]);
	}
}
