package org.dreambig.dsmuscles.leetcode.medium;

import com.sun.source.tree.Tree;

import java.util.*;

public class ClosestLeafInBT {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private void dfs(TreeNode node, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        if (node != null) {
            if (!graph.containsKey(parent))
                graph.put(parent, new LinkedList<>());

            if (!graph.containsKey(node))
                graph.put(node, new LinkedList<>());
            if (node.left != null) {
                graph.get(node).add(node.left);
            }
            if (node.right != null) {
                graph.get(node).add(node.right);
            }
            graph.get(node).add(parent);
            graph.get(parent).add(node);

            dfs(node.left, node, graph);
            dfs(node.right, node, graph);
        }
    }


    public int findClosestLeaf(TreeNode root, int k) {
        // Approach is to first convert tree to adjucent Graph and thn find sortest path between K and root node

        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(root, null, graph);

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode kNode = null;
        for (TreeNode node : graph.keySet()) {
            if (node!=null && node.val == k) {
                kNode = node;
                visited.add(node);
            }
        }
        queue.add(kNode);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                if (graph.get(curr).size() <= 1) {
                    res = curr.val;
                    break;
                }
                for (TreeNode node : graph.get(curr)) {
                    if (!visited.contains(node)) {
                        visited.add(node);
                        queue.add(node);
                    }
                }
            }
        }


        return res;
    }
}
