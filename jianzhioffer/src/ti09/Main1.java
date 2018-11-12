package ti09;

import java.util.Stack;

/*
 * 用两个栈实现队列
 */
public class Main1 {
	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		mq.push(1);
		System.out.println(mq.pull());
		mq.push(2);
		mq.push(3);
		mq.push(4);
		System.out.println(mq.pull());
		System.out.println(mq.pull());
	}
}

class MyQueue{
	Stack<Integer> stk1 = new Stack<Integer>();
	Stack<Integer> stk2 = new Stack<Integer>();
	
	public int pull() {
		if(stk2.isEmpty()) {
			while(!stk1.isEmpty())
				stk2.push(stk1.pop());
		}
		if(!stk2.isEmpty())
			return stk2.pop();
		return -1;
	}
	
	public void push(int val) {
		stk1.push(val);
	}
}