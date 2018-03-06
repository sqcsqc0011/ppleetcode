package Classes;
public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int x) { val = x; }
	
	public static ListNode getNode(int[] nums) {
		ListNode res = new ListNode(-1);
		ListNode cur = res;
		for(int i = 0; i < nums.length; i++) {
			cur.next = new ListNode(nums[i]);
			cur = cur.next;
		}
		return res.next;
	}
}
