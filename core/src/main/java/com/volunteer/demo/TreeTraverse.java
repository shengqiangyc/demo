package com.volunteer.demo;

import com.fasterxml.jackson.core.TreeNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shengqiang
 * @date 2021-03-31 10:07
 */
public class TreeTraverse {

    @Data
    class TreeNode{

        private Integer val;

        private TreeNode left;

        private TreeNode right;
    }

    private void firstTraverse(TreeNode root){
        if (root == null){
            return;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            treeNodes.add(node);
            if (node.getRight() != null){
                stack.push(node.getRight());
            }
            if (node.getLeft() != null){
                stack.push(node.getLeft());
            }
        }
    }

    private List<Integer> middleTraverse(TreeNode root){
        if (root == null){
            return new ArrayList<>();
        }
        List<Integer> treeNodes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            treeNodes.add(current.val);
            current = current.right;
        }
        return treeNodes;
    }

}
