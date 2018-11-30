package ti53;
/*
 * 0~n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
 * 并且每个数字都在范围0~n-1中。在范围0~n-1内的n个数字中有且仅有一个
 * 数字不在该数组中，请找出这个数字。
 */
public class Main2 {
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5,6,8,9};
		System.out.println(solve(arr));
	}
	public static int solve(int[] arr) {
		int begin = 0;
		int end = arr.length-1;
		while(begin <= end) {
			int mid = (begin+end)/2;
			if(arr[mid] != mid) {
				if(mid == 0 || arr[mid-1] == mid-1)
					return mid;
				else {
					end = mid - 1;
				}
			}else {
				if(mid == arr.length-1 || arr[mid+1] != mid+1)
					return mid+1;
				else {
					begin = mid + 1;
				}
			}
		}
		if(begin == arr.length)
			return begin;
		return -1;
		
	}
}
