package com.nott.io.code.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 2017/2/15.
 */
public class Tree {
    private TreeNode root;
    private List<TreeNode> nodeList;

    public static Tree create(List<TreeData> list) {
        List<TreeNode> nodeList = new ArrayList<>();
        for(TreeData data : list) {
            nodeList.add(TreeNode.create(data));
        }
        for(TreeNode node1 : nodeList){
            for(TreeNode node2 : nodeList){
                if(node2.isParent(node1)){
                    node2.addChild(node1);
                    break;
                }
            }
        }
        return createRoot(nodeList);
    }

    private static Tree createRoot(List<TreeNode> list) {

        Tree result = new Tree();
        for(TreeNode node : list){
            if(node.isRoot() || isRoot(list, node)) {
                result.root = node;
                break;
            }
        }
        result.nodeList = list;
        return result;
    }

    /**
     * 如果找不到其父节点，就是根
     */
    private static boolean isRoot(List<TreeNode> list, TreeNode node) {
        for(TreeNode bean : list){
            if (node.getParentId().equals(bean.getId())) {
                return false;
            }
        }
        return true;
    }

    public TreeNode getNode(Long id) {
        for(TreeNode node : nodeList) {
            if(node.getId().equals(id)) {
                return node;
            }
        }
        return TreeNode.createEmpty();
    }

    public TreeNode getRoot() {
        return root;
    }

    public List<TreeNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<TreeNode> nodeList) {
        this.nodeList = nodeList;
    }
}
