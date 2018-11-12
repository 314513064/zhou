package ti03;
/*
 * 数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复
 * 的数字。例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复
 * 的数字2或者3。
 */
/*
 * 思路：如果数组中没有重复的数字，那么可以做到把数字i放到下标为i的地方。
 * 	   如果数组中存在重复数字，那么肯定会出现冲突，用这个冲突来解这题。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {2,3,1,0,2,5,3};
		for(int i = 0;i < arr.length;i++) {
			while(arr[i] != i) {
				if(arr[i] == arr[arr[i]]) {
					System.out.println(arr[i]);
					return;
				}
				int tmp = arr[arr[i]];
				arr[arr[i]] = arr[i];
				arr[i] = tmp;
			}
		}
	}
}
