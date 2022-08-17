package com.volunteer.demo.leetcode;

import com.volunteer.demo.ListNode;

import java.util.*;

/**
 * @author shengqiang
 * @date 2022/4/13 10:29 AM
 */
public class ReSizeListNode {

    public void reorderList(ListNode head) {
        ListNode slow = head,fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        ListNode preNode = null;
        ListNode currentNode = next;
        while (currentNode != null){
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        ListNode reverseNext = preNode;
        ListNode tmp1;
        ListNode tmp2;
        ListNode tmpHead = head;
        ListNode tmpTail = reverseNext;
        while (tmpHead != null && tmpTail != null){
            tmp1 = tmpHead.next;
            tmp2 = tmpTail.next;

            tmpHead.next = tmpTail;
            tmpHead = tmp1;

            tmpTail.next = tmpHead;
            tmpTail = tmp2;
        }
        List list = Arrays.asList(new int[1]);
    }

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet();
        for (int x: nums) set.add(x);
        ListNode tmp = head;
        int size = 0;
        while (tmp != null){
            if (set.contains(tmp.val) && (tmp.next == null || !set.contains(tmp.next.val))){
                size ++;
            }
            tmp = tmp.next;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.isEmpty();
        return size;
    }

}
