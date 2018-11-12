package ti03;
/*
 * 不修改数组找出重复的数字
 * 在一个长度为n+1的数组里所有数字都在1~n的范围内，所以数组中
 * 至少有一个数字是重复的。请找出任意一个重复的数字，但不能修改数组。
 * 如{2,3,5,4,3,2,6,7}，输出2或者3。
 */
/*
 * 使用了二分的思想解这一题
 */
public class Main2 {
	public static void main(String[] args) {
		int[] arr = {2,3,5,4,3,2,6,7};
		solve(arr);
	}
	public static void solve(int[] arr) {
		int start = 1;
		int end = arr.length;
		while(start < end) {
			int mid = (end - start)/2 + start;
			int leftCount = 0;
			int rightCount = 0;
			for(int i = 0;i < arr.length;i++) {
				if(arr[i] >= start && arr[i] <= mid)
					leftCount++;
				else
					rightCount++;
			}
			if(leftCount > mid - start + 1)
				end = mid;
			else
				start = mid+1;
		}
		System.out.println(start);
	}
}
