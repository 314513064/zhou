package ti44;
/*
 * 数字序列中某一位的数字
 * 数字以01234567891011121314......的格式序列化到一个字符序列中。
 * 在这个序列中，第5位是5(从0开始计数)，第13位为1，第19位是4。
 * 写一个函数，求任意第n位对应的数字。
 */
public class Main1 {
	public static void main(String[] args) {
		int num = 16;
		int digit = getDigit(num);
		int firstIndexOfDigit = 0;
		int firstNumOfDigit = 1;
		for(int i = 1;i < digit;i++) {
			firstNumOfDigit *= 10;
			firstIndexOfDigit += i * firstNumOfDigit;
		}
		if(firstNumOfDigit == 1)
			firstNumOfDigit = 0;
		int count = (num - firstIndexOfDigit)/digit;
		int index = (num - firstIndexOfDigit)%digit;
		String strNum = ""+(firstNumOfDigit+count);
		System.out.println(strNum.charAt(index));
	}
	
	public static int getDigit(int num) {
		int digit = 0;
		int i = 1;
		int j = 10;
		while(num > 0) {
			num -= i*j;
			i++;
			j *= 10;
			digit++;
		}
		return digit;
	}
}
