package ti21;
/*
 * ��������˳��ʹ����λ��ż��ǰ��
 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��
 * ʹ����������λ�������ǰ�벿�֣�����ż��λ�ں�벿��
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
