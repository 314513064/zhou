package ti22;


/*
 * 链表中倒数第K个节点
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.nextNode = new ListNode(2);
		head.nextNode.nextNode = new ListNode(3);
		head.nextNode.nextNode.nextNode = new ListNode(4);
		head.nextNode.nextNode.nextNode.nextNode = new ListNode(5);
		int n = 2;
		solve(head, n);
	}
	
	public static void solve(ListNode head,int n) {
		if(n <= 0 || head == null) {
			System.out.println("error");
			return;
		}
		ListNode pre = head;
		ListNode last = head;
		for(int i = 0;i < n;i++) {
			if(last.nextNode == null) {
				System.out.println("error");
				return;
			}
			last = last.nextNode;
		}
		while(last != null) {
			last = last.nextNode;
			pre = pre.nextNode;
		}
		System.out.println(pre.value);
	}
}

class ListNode{
	int value;
	ListNode nextNode;
	public ListNode(int value) {
		this.value = value;
	}
}