package ti21;
/*
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个正数数组，实现一个函数来调整该数组中数字的顺序
 * 使得所有奇数位于数组的前半部分，所有偶数位于后半部分
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {1,3,5,6,7,213,4};
		int oddIndex = 0;
		int evenIndex = 0;
		while(evenIndex < arr.length) {
			if(arr[evenIndex] % 2 == 1) {
				int tmp = arr[evenIndex];
				arr[evenIndex] = arr[oddIndex];
				arr[oddIndex] = tmp;
				oddIndex++;
			}
			evenIndex++;
		}
		for(int a:arr) {
			System.out.print(a + "  ");
		}
	}
}
