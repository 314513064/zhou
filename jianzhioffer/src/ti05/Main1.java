package ti05;
/*
 * 替换空格
 * 请实现一个函数，把字符串中的每个空格替换成“%20”。
 */
public class Main1 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("We are happy.");
		int originalLen = sb.length();
		for(int i = 0;i < originalLen;i++) {
			if(sb.charAt(i) == ' ') {
				sb.append("  ");
			}
		}
		int indexLast = sb.length() - 1;
		for(int i = originalLen-1;i >= 0;i-- ) {
			if(sb.charAt(i) == ' ') {
				sb.setCharAt(indexLast--, '0');
				sb.setCharAt(indexLast--, '2');
				sb.setCharAt(indexLast--, '%');
			}else {
				sb.setCharAt(indexLast--, sb.charAt(i));
			}
		}
		System.out.println(sb.toString());
	}
}
