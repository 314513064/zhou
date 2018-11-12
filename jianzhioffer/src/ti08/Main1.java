package ti08;
/*
 * 二叉树的下一个节点
 * 给定一颗二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点。
 * 该数中序遍历为{d,b,h,e,i,a,f,c,g}
 */
public class Main1 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode('a');
		root.leftChild = new TreeNode('b');
		root.leftChild.parent = root;
		root.leftChild.leftChild = new TreeNode('d');
		root.leftChild.leftChild.parent = root.leftChild;
		
		root.leftChild.rightChild = new TreeNode('e');
		root.leftChild.rightChild.parent = root.leftChild;
		
		root.leftChild.rightChild.leftChild = new TreeNode('h');
		root.leftChild.rightChild.leftChild.parent = root.leftChild.rightChild;
		
		root.leftChild.rightChild.rightChild = new TreeNode('i');
		root.leftChild.rightChild.rightChild.parent = root.leftChild.rightChild;
		
		root.rightChild = new TreeNode('c');
		root.rightChild.parent = root;
		
		root.rightChild.leftChild = new TreeNode('f');
		root.rightChild.leftChild.parent = root.rightChild;
		
		root.rightChild.rightChild = new TreeNode('g');
		root.rightChild.rightChild.parent = root.rightChild;
		
		TreeNode target = root.leftChild.rightChild.rightChild; //i
		solve(target);
	}
	
	public static void solve(TreeNode target) {
		if(target.rightChild != null) {
			TreeNode tmp = target.rightChild;
			while(tmp.leftChild != null) {
				tmp = tmp.leftChild;
			}
			System.out.println(tmp.val);
			return;
		}else if(target.parent != null) {
			TreeNode tmp = target.parent;
			while(tmp != null && tmp.parent.rightChild == tmp) {
				tmp = tmp.parent;
			}
			System.out.println(tmp.parent.val);
			return;
		}
	}
}

class TreeNode{
	char val;
	TreeNode parent;
	TreeNode leftChild;
	TreeNode rightChild;
	public TreeNode(char val) {
		this.val = val;
	}
}