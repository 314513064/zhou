package ti31;

import java.util.Stack;

/*
 * 栈的压入、弹出序列
 * 输入两个整数数列，第一个数列表示栈的压入顺序，请判断
 * 第二个数列是否为该栈的弹出顺序。假设压入栈的所有数字
 * 均不相等。
 */
public class Main1 {
	public static void main(String[] args) {
		int[] input = {1,2,3,4,5};
		int[] output = {4,5,2,1,3};
		//{4,3,5,1,2}
		System.out.println(solve(input, output));
	}
	
	public static boolean solve(int[] input,int[] output) {
		if(input.length != output.length)
			return false;
		int inIndex = 0;
		int outIndex = 0;
		Stack<Integer> stk = new Stack<Integer>();
		while(outIndex < output.length) {
			if(inIndex < input.length){
				if(input[inIndex] != output[outIndex]) {
					if(!stk.isEmpty() && stk.peek() == output[outIndex]) {
						stk.pop();
						outIndex++;
					}else {
						if(inIndex == input.length-1 && input[inIndex] != output[outIndex])
							return false;
						stk.push(input[inIndex++]);
					}
				}else {
					inIndex++;
					outIndex++;
				}
			}else {
				if(stk.pop() != output[outIndex++]) {
					return false;
				}
			}
			
		}
		return true;
	}
}
