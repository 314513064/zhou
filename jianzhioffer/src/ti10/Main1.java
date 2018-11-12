package ti10;
/*
 * 斐波那契数列
 * 0,1,1,2,3,5,8......
 */
public class Main1 {
	public static void main(String[] args) {
		int n = 6;
		System.out.println(solve(n));
	}
	public static int solve(int n) {
		if( n == 0 )
			return 0;
		if( n == 1)
			return 1;
		int pre = 0;
		int cur = 1;
		int res = 0;
		for(int i = 1;i < n;i++) {
			res = pre + cur;
			pre = cur;
			cur = res; 
		}
		return res;
	}
}
