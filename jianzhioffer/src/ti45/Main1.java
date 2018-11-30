package ti45;
/*
 * 把数组排成最小数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {3,32,321};
		solve(arr);
	}
	public static void solve(int[] arr) {
		for(int i = 0;i < arr.length;i++) {
			for(int j = 0;j < arr.length-i-1;j++) {
				String str1 = ""+arr[j]+arr[j+1];
				String str2 = ""+arr[j+1]+arr[j];
				if(Integer.parseInt(str1) > Integer.parseInt(str2)) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
		for(int a:arr) {
			System.out.print(a);
		}
	}
}
