package ti49;
/*
 * 丑数
 * 我们把只包含因子2、3、5的数称作丑数。
 * 求按从小到大的顺序的第n个丑数。习惯上把1当作第一个丑数。
 */
public class Main1 {
	public static void main(String[] args) {
		int n = 1500;
		long[] arr = new long[n];
		arr[0] = 1;
		arr[1] = 2;
		for(int i = 2;i < arr.length;i++) {
			int min2Index = 0;
			int min3Index = 0;
			int min5Index = 0;
			boolean flag2 = false;
			boolean flag3 = false;
			boolean flag5 = false;
			long min = 0;
			for(int j = i-2;j >= 0;j--) {
				if(!flag2 && arr[j]*2 <= arr[i-1]) {
					min2Index = j+1;
					flag2 = true;
				}
				if(!flag3 && arr[j]*3 <= arr[i-1]) {
					min3Index = j+1;
					flag3 = true;
				}
				if(!flag5 && arr[j]*5 <= arr[i-1]) {
					min5Index = j+1;
					flag5 = true;
				}
				if(flag2 && flag3 && flag5)
					break;
				if(j == 0) {
					if(!flag2)
						min2Index = j;
					if(!flag3)
						min3Index = j;
					if(!flag5)
						min5Index = j;
				}
			}
			min = Math.min(arr[min2Index]*2, arr[min3Index]*3);
			min = Math.min(min, arr[min5Index]*5);
			arr[i] = min;
		}
		System.out.println(arr[arr.length-1]);
	}
}
