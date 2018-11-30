package ti53;
/*
 * 在排序数组中查找数字
 * 数字在排序数组中出现的次数。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,3,3,3,3,4,5,6,6};
		int target = 6;
		System.out.println(solve(arr, 0, arr.length-1, target));
	}
	
	public static int solve(int[] arr,int begin, int end, int target) {
		if(begin > end)
			return 0;
		int mid = (end - begin)/2 + begin;
		if(arr[mid] > target) {
			return solve(arr, begin, mid-1,target);
		}else if(arr[mid] < target){
			return solve(arr,mid+1,end,target);
		}else {
			int first = getFirst(arr, begin, mid, target);
			int last = getLast(arr, mid, end, target);
			return last-first+1;
		}
	}
	
	public static int getFirst(int arr[],int begin, int end, int target) {
		if(end == 0 || arr[end] != arr[end-1])
			return end;
		
		int mid = (end - begin)/2 + begin;
		if(arr[mid] < target)
			return getFirst(arr, mid+1, end, target);
		return getFirst(arr, begin, mid-1, target);
	}
	
	public static int getLast(int arr[], int begin , int end, int target) {
		if(begin == (arr.length-1) || arr[begin] != arr[begin+1])
			return begin;
		
		int mid = (end - begin)/2 + begin;
		if(arr[mid] > target)
			return getLast(arr, begin, mid-1, target);
		return getLast(arr, mid+1, end, target);
	}
}
