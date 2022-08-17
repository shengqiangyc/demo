package com.volunteer.demo;

/**
 * 链表插入排序
 * @author shengqiang
 * @date 2020-11-20 09:57
 */
public class NodeInsert {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public ListNode insertionSortList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode endNode = head;
        ListNode currentNode = head.next;
        while (currentNode != null){
            if (currentNode.val >= endNode.val){
                endNode = endNode.next;
            } else {
                ListNode preNode = dummyHead;
                while (preNode.next.val < currentNode.val){
                    preNode = preNode.next;
                }
                endNode.next = currentNode.next;
                currentNode.next = preNode.next;
                preNode.next = currentNode;
            }
            currentNode = endNode.next;
        }
        return dummyHead.next;
    }
}
