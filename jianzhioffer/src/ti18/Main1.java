package ti18;
/*
 * ɾ������ڵ�
 * �������������ͷָ���һ���ڵ�ָ�룬����һ��������O(1)ʱ����ɾ���ýڵ㡣
 */
/*
 * ˼·����Ŀ��ڵ��ֵ��ΪĿ��ڵ���һ���ڵ��ֵ��Ȼ��ɾ����һ���ڵ㣬���Ŀ��ڵ���β�ڵ㣬���ǵñ�������
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.nextNode = new ListNode(2);
		head.nextNode.nextNode = new ListNode(3);
		head.nextNode.nextNode.nextNode = new ListNode(4);
		ListNode target = head.nextNode.nextNode.nextNode;
		
		if(target.nextNode != null) {
			target.value = target.nextNode.value;
			target.nextNode = target.nextNode.nextNode;
		}else {
			ListNode tmp = head;
			while(tmp.nextNode != target)
				tmp = tmp.nextNode;
			tmp.nextNode = tmp.nextNode.nextNode;
		}
		while(head != null) {
			System.out.print(head.value + "  ");
			head = head.nextNode;
		}
	}
}

class ListNode{
	int value;
	ListNode nextNode;
	public ListNode(int value) {
		this.value = value;
	}
}
