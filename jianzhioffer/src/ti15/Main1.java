package ti15;
/*
 * 二进制中1的个数
 * 请实现一个函数，输入一个整数，输出该二进制表示中1的个数。
 * 例如，把9表示成二进制是1001，有2为是1。
 */
public class Main1 {
	public static void main(String[] args) {
		System.out.println(solve(9));
	}
	
	public static int solve(int num) {
		int count = 0;
		while(num != 0) {
			count ++;
			num = num & (num-1);
		}
		return count ;
	}
}
