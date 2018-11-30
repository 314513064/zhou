package ti43;
/*
 * 1~n整数中1出现的次数
 * 输入一个正数n，求1~n这n个整数的十进制表示中1出现的次数。
 */
public class Main1 {
	public static void main(String[] args) {
		
		System.out.println(solve(12));
	}
	
	public static int solve(int num) {
		if(num == 0)
			return 0;
		int res = 0;
		int digit = getDigit(num);
		if(digit == 1) {
				return 1;
		}
		int firstNumOfDigit = 1;
		for(int i = 1;i < digit;i++) {
			firstNumOfDigit *= 10;
		}
		if(num/firstNumOfDigit == 1) {
			res += num%firstNumOfDigit + 1;
			res += (digit - 1)*(int)Math.pow(10, digit-2);
		}else {
			res += firstNumOfDigit;
			res += (num/firstNumOfDigit)*(digit - 1)*(int)Math.pow(10, digit-2);
		}
		return res + solve(num%firstNumOfDigit);
	}
	
	public static int getDigit(int num) {
		int digit = 0;
		while(num != 0) {
			digit++;
			num /= 10;
		}
		return digit;
	}
}
