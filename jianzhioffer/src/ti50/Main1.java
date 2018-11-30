package ti50;
/*
 * 第一次只出现一次的字符串(这里说的是字符，我不管，我只管小写字母)
 * 字符串中第一个只出现一次的字符。
 */
public class Main1 {
	public static void main(String[] args) {
		String str = "abaccdeff";
		int[] count = new int[26];
		for(int i = 0;i < str.length();i++) {
			count[str.charAt(i)-'a']++;
		}
		for(int i = 0;i < str.length();i++) {
			if(count[str.charAt(i)-'a'] == 1) {
				System.out.println(str.charAt(i));
				break;
			}
		}
	}
}
