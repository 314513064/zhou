package ti32;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 之子形打印二叉树
 * 请实现一个函数按照之字形顺序打印二叉树，
 * 即第一行按从左到右，第二行按从右到左的顺序打印。
 */
public class Main3 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode('a');
		root.leftChild = new TreeNode('b');
		root.leftChild.leftChild = new TreeNode('d');
		root.leftChild.rightChild = new TreeNode('e');
		root.leftChild.rightChild.leftChild = new TreeNode('h');
		root.leftChild.rightChild.rightChild = new TreeNode('i');
		root.rightChild = new TreeNode('c');
		root.rightChild.leftChild = new TreeNode('f');
		root.rightChild.rightChild = new TreeNode('g');
		
		solve(root);
	}
	public static void solve(TreeNode root) {
		boolean flag = true;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Stack<TreeNode> stk =  new Stack<TreeNode>();
		queue.offer(root);
		int count = 1;
		int nextCount = 0;
		while(!queue.isEmpty()) {
			nextCount = 0;
			if(flag) {
				flag = !flag;
				for(int i = 0;i < count;i++) {
					TreeNode tmp = queue.poll();
					stk.push(tmp);
					System.out.print(tmp.val + " ");
				}
				while(!stk.isEmpty()) {
					TreeNode tmp = stk.pop();
					if(tmp.rightChild != null) {
						queue.offer(tmp.rightChild);
						nextCount++;
					}
					if(tmp.leftChild != null) {
						queue.offer(tmp.leftChild);
						nextCount++;
					}
				}
			}else {
				flag = !flag;
				for(int i = 0;i < count;i++) {
					TreeNode tmp = queue.poll();
					stk.push(tmp);
					System.out.print(tmp.val + " ");
				}
				while(!stk.isEmpty()) {
					TreeNode tmp = stk.pop();
					if(tmp.leftChild != null) {
						queue.offer(tmp.leftChild);
						nextCount++;
					}
					if(tmp.rightChild != null) {
						queue.offer(tmp.rightChild);
						nextCount++;
					}
				}
			}
			System.out.println();
			count = nextCount;
		}
	}
}
