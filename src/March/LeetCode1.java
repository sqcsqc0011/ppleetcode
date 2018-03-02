package March;

import java.util.HashMap;

import Classes.ListNode;

public class LeetCode1 {

	
	//1. Two Sum
	public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
        		if(maps.containsKey(target - nums[i])) {
        			return new int[] {maps.get(target - nums[i]), i};
        		}
            	maps.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
	
	//2. Add Two Numbers
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(-1);
		ListNode head = res;
		int over = 0;
		while(l1 != null || l2 != null || over != 0) {
			int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + over;
			over = num/10;
			num = num%10;
			head.next = new ListNode(num);
			head = head.next;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
        return res.next;
    }
	
	//3. Longest Substring Without Repeating Characters
	public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, max = 0;
        int[] chs = new int[128];
        for(; j < s.length(); j++) {
			i = Math.max(chs[s.charAt(j)], i);
    			max = Math.max(j + 1 - i, max);
    			chs[s.charAt(j)] = j + 1;
        }
        return max;
    }
	
}
