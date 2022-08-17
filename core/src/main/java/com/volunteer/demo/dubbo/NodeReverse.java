/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/10/18下午2:09
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

import java.util.Stack;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/10/18下午2:09
 * sinceV1.0
 */
public class NodeReverse {

    class Node {
        int data;
        Node next;
    }

    private void reverseNode(Node head){
        Stack<Node> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()){
            Node newHead = stack.pop();
            newHead.next = stack.peek();
        }
    }

    private void reverseNodeByFor(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    private void doubleReverse(Node head){
        Node dummy = new Node();
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null){
            Node a = head.next;
            Node b = head.next.next;
            head.next = b;
            a.next = b.next;
            b.next = a;
            head = a;
        }
    }
}
