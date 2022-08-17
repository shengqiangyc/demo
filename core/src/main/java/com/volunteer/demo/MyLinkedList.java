package com.volunteer.demo;

/**
 * @author shengqiang
 * @date 2022/1/21 6:07 下午
 */
class MyLinkedList {
    int size;
    ListNode head;

    public MyLinkedList() {
        int size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        ListNode start = head.next;
        if (index > size - 1 || index < 0){
            return -1;
        }
        int i = 0;
        while (start != null && i != index){
            start = start.next;
            i ++;
        }
        return start.val;
    }

    public void addAtHead(int val) {
        ListNode listNode = new ListNode(val);
        ListNode oldHeadNext = head.next;
        head.next = listNode;
        listNode.next = oldHeadNext;
        size ++;
    }

    public void addAtTail(int val) {
        ListNode tailHead = head.next;
        while (tailHead != null && tailHead.next != null){
            tailHead = tailHead.next;
        }
        tailHead.next = new ListNode(val);
        size ++;

    }

    public void addAtIndex(int index, int val) {
        if (index <= 0){
            addAtHead(val);
        } else if (index == size){
            addAtTail(val);
        } else if (index < size){
            ListNode node = new ListNode(val);
            ListNode indexNode = head.next;
            int i = 0;
            while (indexNode != null && indexNode.next != null && i != index - 1){
                indexNode = indexNode.next;
                i ++;
            }
            node.next = indexNode.next;
            indexNode.next = node;
            size ++;
        }

    }

    public void deleteAtIndex(int index) {
        if (index < size && size > 0){
            ListNode indexNode = head;
            if (indexNode.next == null){
                return;
            }
            int i = 0;
            while (indexNode.next != null && i <= index - 1){
                indexNode = indexNode.next;
                i ++;
            }
            indexNode.next = indexNode.next.next;
            size --;
        }
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
        //int param_1 = obj.get(index);
        obj.addAtHead(1);
        //obj.addAtTail(val);
        //obj.addAtIndex(index,val);
        obj.deleteAtIndex(0);
    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
}


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
