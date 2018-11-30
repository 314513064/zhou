package ti17;
/*
 * 打印从1到最大的n位数
 * 输入数字n，按顺序打印出从1到最大的n为十进制数。
 */
public class Main1 {
	public static void main(String[] args) {
		int n = 3;
		char[] arr = new char[n+1];
		for(int i = 0;i < arr.length;i++) {
			arr[i] = '0';
		}
		while(true) {
			int index = n;
			if(arr[index] == '9') {
				arr[index--] = '0';
				while(arr[index] == '9') {
					arr[index--] = '0';
				}
				arr[index]++;
				if(arr[0] == '1')
					return;
			}else {
				arr[index]++;
			}
			int printIndex = 0;
			while(arr[printIndex] == '0')
				printIndex++;
			for(int i = printIndex;i < arr.length;i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		}
	}
}
