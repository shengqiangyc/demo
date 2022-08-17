/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/25下午3:44
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/9/25下午3:44
 * sinceV1.0
 */
public class NodeTest {

    private int val;
    private NodeTest left;
    private NodeTest right;

    //前序遍历
    public void firstEntryTree(NodeTest root){
        Stack<NodeTest> stack = new Stack<>();
        if (root == null){
            return;
        }
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                System.out.println(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right ;
        }
    }

    //中序遍历
    public void middleEntryTree(NodeTest root){
        Stack<NodeTest> stack = new Stack<>();
        if (root == null){
            return;
        }
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.println(root.val);
            root = root.right;
        }
    }

    //后序遍历
    public void lastEntryTree(NodeTest nodeTest){
        Stack<NodeTest> stack = new Stack<>();
        Map<NodeTest,Boolean> nodeTestBooleanMap = new HashMap<>();
        while (!stack.isEmpty() || nodeTest != null){
            while (nodeTest != null){
                stack.push(nodeTest);
                nodeTest = nodeTest.left;
            }
            nodeTest = stack.peek();
            if (nodeTest.right == null && nodeTestBooleanMap.containsKey(nodeTest)){
                System.out.println(nodeTest.val);
                stack.pop();
                nodeTest = null;
                nodeTestBooleanMap.put(nodeTest,true);
            } else {
                nodeTest = nodeTest.right;
            }
        }

    }


    public int height(NodeTest root){
        if (root == null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }

}
