package ti53;
/*
 * 数组中数值和下标相等的元素
 * 假设一个单调递增数组里的每个元素都是整数并且是唯一的。
 * 请实现一个函数，找出数组中任意一个数值等于其下标的元素。
 */
public class Main3 {
	public static void main(String[] args) {
		int[] arr = {-3,-1,1,3,5};
		System.out.println(solve(arr));
	}
	public static int solve(int[] arr) {
		int begin = 0;
		int end = arr.length-1;
		while(begin <= end) {
			int mid = (begin + end)/2;
			if(arr[mid] == mid)
				return mid;
			if(arr[mid] < mid) {
				begin = mid+1;
			}
			if(arr[mid] > mid) {
				end = mid - 1;
			}
		}
		return -99999;
	}
}
