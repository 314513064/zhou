package ti16;
/*
 * ��ֵ�������η�
 * ʵ�ֺ���double Power(double base, int exponent)����base��
 * exponent�η�������ʹ�ú����⡣
 */
public class Main1 {
	public static void main(String[] args) {
		
	}
	
	public static double power(double base, int exponent) {
		if(base - 0 == 0 && exponent < 0) {
			return -999999;  //error
		}
		double res = 1.0;
		boolean flag = false;
		if(exponent < 0) {
			flag = true;
			exponent = -exponent;
		}
		for(int i = 0;i < exponent;i++) {
			res *= base;
		}
		return flag ? 1.0/res : res;
	}
	public static double powerPositive(double base, int exponentAbs) {
		if(exponentAbs == 0)
			return 1;
		if(exponentAbs == 1)
			return base;
		double res = powerPositive(base, exponentAbs/2);
		return res;
	}
}
