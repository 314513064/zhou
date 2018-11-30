package ti38;
/*
 * 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排序。
 * 例如，输入字符串abc，则打印出a、b、c能排列出来的所有字符串。
 */
public class Main1 {
	public static void main(String[] args) {
		String str = "abc";
		//pailie(str, "");
		zuhe(str, "", 2);
	}
	
	public static void pailie(String str,String now) {
		if(str.length() == 0) {
			System.out.println(now);
			return;
		}
		for(int i = 0;i <str.length();i++) {
			char c = str.charAt(i);
			pailie(str.substring(0, i)+str.substring(i+1,str.length()), now+c);
		}
	}
	
	public static void zuhe(String str,String now,int len) {
		if(len > str.length())
			return;
		if(len == 0) {
			System.out.println(now);
			return;
		}
		char c = str.charAt(0);
		zuhe(str.substring(1,str.length()), now+c, len-1);
		zuhe(str.substring(1, str.length()),now, len);
	}
}
