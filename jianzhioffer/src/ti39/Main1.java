package ti39;
/*
 * 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,2,2,2,5,4,2};
		System.out.println(arr[partition(arr, 0, arr.length-1)]);
	}
	
	
	public static int partition(int[] arr,int begin,int end) {
		int x = arr[end];
		int index = begin;
		for(int i = begin;i < end;i++) {
			if(arr[i] <= x) {
				int tmp = arr[i];
				arr[i] = arr[index];
				arr[index] = tmp;
				index++;
			}
		}
		arr[end] = arr[index];
		arr[index] = x;
		if(index == arr.length/2)
			return index;
		else if(index > arr.length/2) {
			return partition(arr, begin, index-1);
		}else {
			return partition(arr, index+1, end);
		}
	}
	
}
