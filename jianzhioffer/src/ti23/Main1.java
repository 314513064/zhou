package ti23;


/*
 * 链表中环的入口节点
 * 如果一个入口中包含环，如何找出环的入口节点。
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.nextNode = new ListNode(2);
		head.nextNode.nextNode = new ListNode(3);
		head.nextNode.nextNode.nextNode = new ListNode(4);
		head.nextNode.nextNode.nextNode.nextNode = new ListNode(5);
		head.nextNode.nextNode.nextNode.nextNode.nextNode = new ListNode(6);
		head.nextNode.nextNode.nextNode.nextNode.nextNode.nextNode = head.nextNode.nextNode.nextNode;
		solve(head);
	}
	
	public static void solve(ListNode head) {
		ListNode quick = head;
		ListNode slow = head;
		while(true) {
			quick = quick.nextNode.nextNode;
			slow = slow.nextNode;
			if(slow == quick)
				break;
		}
		quick = quick.nextNode;
		int count = 1;
		while(quick != slow) {
			quick = quick.nextNode;
			count ++;
		}
		
		quick = head;
		for(int i = 0;i < count;i++) {
			quick = quick.nextNode;
		}
		slow = head;
		while(slow != quick) {
			slow = slow.nextNode;
			quick = quick.nextNode;
		}
		System.out.println(slow.value);
	}
}

class ListNode{
	int value;
	ListNode nextNode;
	public ListNode(int value) {
		this.value = value;
	}
}