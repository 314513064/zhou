package ti18;
/*
 * 删除排序链表中重复的节点
 */
public class Main2 {
	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		head.nextNode = new ListNode(3);
		head.nextNode.nextNode = new ListNode(3);
		head.nextNode.nextNode.nextNode = new ListNode(4);
		head.nextNode.nextNode.nextNode.nextNode = new ListNode(4);
		head.nextNode.nextNode.nextNode.nextNode.nextNode = new ListNode(7);
		head.nextNode.nextNode.nextNode.nextNode.nextNode.nextNode = new ListNode(7);
		
		head = solve(head);
		
		while(head != null) {
			System.out.print(head.value + "  ");
			head = head.nextNode;
		}
	}
	public static ListNode solve(ListNode head) {
		if(head.nextNode == null) return head;
		ListNode res = new ListNode(Integer.MAX_VALUE);
		res.nextNode = head;
		ListNode pre = res;
		ListNode cur = res.nextNode;
		boolean isRepeated = false;
		while(cur != null) {
			if(cur.nextNode == null) {
				if(isRepeated) {
					pre.nextNode = null;
				}
			} else {
				if(cur.value == cur.nextNode.value) {
					isRepeated = true;
				} else {
					if(isRepeated) {
						isRepeated = false;
						pre.nextNode = cur.nextNode;
					}else {
						pre = pre.nextNode;
					}
				}
			}
			cur = cur.nextNode;
		}
		return res.nextNode;
	}
}
