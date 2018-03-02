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
	
	//4. Median of Two Sorted Arrays
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length > nums2.length) return findMedianSortedArraysHelper(nums2, nums1);
		return findMedianSortedArraysHelper(nums1, nums2);
    }

	public double findMedianSortedArraysHelper(int[] A, int[] B) {
		int m = A.length, n = B.length, halfLen = (m + n + 1)/2;
		int iMin = 0, iMax = m, i = 0, j = 0;
		while(iMin <= iMax) {
			i = (iMin + iMax)/2;
			j = halfLen - i;
			if(i > iMin && A[i - 1] > B[j]) {
				iMax--;
			} else if (i < iMax && A[i] < B[j - 1]) {
				iMin++;
			} else {
				int MaxL = 0;
				if(i == 0) MaxL = B[j - 1];
				else if(j == 0) MaxL = A[i - 1];
				else MaxL = Math.max(A[i - 1], B[j - 1]);
				if( (m + n)%2 == 1) return MaxL;
				int MinR = 0;
				if(i == m) MinR = B[j];
				else if(j == n) MinR = A[i];
				else MinR = Math.min(A[i], B[j]);
				return (MaxL + MinR)/2.0;
			}
		}
		return 0;
	}
	
	//5. Longest Palindromic Substring
	public String longestPalindrome(String s) {
		int l = 0, r = 0;
		for(int i = 0; i < s.length(); i++) {
			int len1 = longestPalindromeHelper(s, i, i);
			int len2 = longestPalindromeHelper(s, i, i + 1);
			int len = Math.max(len1, len2);
			if(len > r - l + 1) {
				l = i - (len - 1)/2;
				r = i + len/2;
			}
		}
		return s.substring(l, r + 1);
    }
	private int longestPalindromeHelper(String s, int l, int r) {
		while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}
	
}
