package ti11;
/*
 * 旋转数组的最小数字
 * 把一个数组最开始的若干元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个增序排序数组的一个旋转，输出旋转数组的最小元素。例如，
 * 数组{3,3,3,1,2}为{1,2,3,4,5}的一个旋转，该数组最小值为1。
 */
/*
 * 使用二分的思想，但要考虑{1,1,0,1,1,1}这样的特殊情况,还要考虑{1,2,3,4}这种未翻转的情况。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {1,1,1,0,1,1,1,1,1,1};
		System.out.println(solve(arr));
	}
	public static int solve(int[] arr) {
		int start = 0;
		int end = arr.length-1;
		int min = Integer.MAX_VALUE;
		int mid = start;
		while(arr[start] >= arr[end]) {
			if(end - start == 1) {
				mid = end;
				break;
			}
			mid = (end - start)/2 + start;
			if(arr[mid] == arr[start] && arr[mid] == arr[end]) {
				for(int i = start;i <= end;i++) {
					if(min > arr[i])
						min = arr[i];
				}
				return min;
			}
			if(arr[mid] >= arr[start]) {
				start = mid;
			}else if(arr[mid] <= arr[end]) {
				end = mid;
			}
		}
		min = arr[mid];
		return min;
	}
}
