/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/25上午10:47
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

import lombok.Data;

import java.util.*;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/9/25上午10:47
 * sinceV1.0
 */
@Data
  class Node {

    private int val;

    private Node left;

    private Node right;


    static class BinaryTree{

        //前序遍历(非递归)
        public void preOrderRec(Node root) {
            if (root == null){
                return;
            }
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null){
                    System.out.println(root.val);
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                root = root.right;
            }
            return;
        }

        //中序遍历
        public void middleOrderRec(Node root){
            Stack<Node> stack = new Stack<>();
            stack.push(root);
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
        public void endOrderRec(Node root){
            Stack<Node> stack = new Stack<>();
            //当前节点
            Node currentNode = root;
            //用于判断树节点是否被遍历过
            Map<Node,Boolean> nodeBooleanMap = new HashMap<>();
            //当栈为空并且当前节点为null的时候，循环结束
            while (!stack.isEmpty() || currentNode != null){
                //先将最左边的节点依次放入栈中
                while (currentNode != null){
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }
                //获取栈顶节点（最左节点）
                currentNode = stack.peek();
                //如果该节点没有右节点，或者该节点的右节点已经被遍历过，则输出当前节点
                if (currentNode.right == null || nodeBooleanMap.containsKey(currentNode.right)){
                    System.out.println(currentNode.val);
                    stack.pop();
                    nodeBooleanMap.put(currentNode,true);
                    currentNode = null;
                    //否则将当前节点置为右节点
                } else {
                    currentNode = currentNode.right;
                }
            }
        }

    }
}
