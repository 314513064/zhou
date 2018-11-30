package ti30;

import java.util.Stack;

/*
 * 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素min的函数
 */
public class Main1 {
	public static void main(String[] args) {
		
	}
	class MyStack{
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> minStk = new Stack<Integer>();
		public void push(int val) {
			stack.push(val);
			if(minStk.isEmpty()) {
				minStk.push(val);
			}else {
				int peek = minStk.peek();
				if(peek > val)
					peek = val;
				minStk.push(peek);
			}
		}
		public int pop() {
			minStk.pop();
			return stack.pop();
		}
		public int min() {
			//没做错处处理
			return minStk.peek();
		}
	}
}
