package com.nott.sort.concrete.recursive;

import java.util.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/10 18:53
 * @Modified By:
 **/
public class Recursive_2 {

    public static void main(String[] args) {
        List<Long> permissionIds = getPermissionIds();
        /**
         * 443
         *      315
         *              563
         *                      564
         *
         *      314
         *              565
         */
    }

    private static List<Long> getPermissionIds(Relationship tenant) {
        List<Long> groupIds = new ArrayList<>();
        groupIds.add(563L);groupIds.add(564L);groupIds.add(565L);
        if (groupIds.contains(tenant.getPartyIdFrom())) {
            return groupIds;
        }
        Map<Long, NodeVo> treeNodes = new HashMap<>();
        treeNodes = findAllNode(tenant, 1, treeNodes); // 列出每个部门所在的层级信息
        Map<Integer, List<NodeVo>> nodes = new HashMap<>();
        int deepLevel = 0;
        int minLevel = 0;
        for (Long currentPartyId : groupIds) {
            NodeVo currentNode = treeNodes.get(currentPartyId);
            // 获得所属部门的最深层级
            if (currentNode.getLevel() > deepLevel) {
                deepLevel = currentNode.getLevel();
            }
            if (minLevel == 0 || currentNode.getLevel() < minLevel) {
                minLevel = currentNode.getLevel();
            }
            // 可见的部门加入到层级信息的map，用于找到所有应该可见的部门信息
            List<NodeVo> nodeVos = nodes.get(currentNode.getLevel()) == null ? new ArrayList<>() : nodes.get(currentNode.getLevel());
            if (nodeVos.contains(currentNode)) {
                continue;
            }
            nodeVos.add(currentNode);
            nodes.put(currentNode.getLevel(), nodeVos);
        }
        if (minLevel == 2) {
            return groupIds;
        }
        List<Long> permissionIds = collectNodeId(treeNodes, nodes, minLevel, deepLevel);
        permissionIds.addAll(groupIds);
        Set<Long> ids = new HashSet<>(permissionIds);
        permissionIds = new ArrayList<>(ids);
        return permissionIds;
    }
    private static Map<Long, NodeVo> findAllNode(Relationship parent, int level, Map<Long, NodeVo> treeNodes) {
        NodeVo current = new NodeVo(level, parent.getPartyIdTo(), parent.getPartyIdFrom());
        treeNodes.put(parent.getPartyIdFrom(), current);
        level++;
        List<Relationship> children = relationshipQueryService.findChildren(parent.getTenantId(), parent.getPartyIdFrom());
        for (Relationship child : children) {
            treeNodes = findAllNode(child, level, treeNodes);
        }
        return treeNodes;
    }
    private static List<Long> collectNodeId(Map<Long, NodeVo> treeNodes, Map<Integer, List<NodeVo>> nodes, int minLevel, int deepLevel) {
        List<Long> nodeIds = new ArrayList<>();
        List<NodeVo> nodeVos = nodes.get(deepLevel);
        for (NodeVo nodeVo : nodeVos) {
            nodeIds = getSubIds(treeNodes, nodeVo, minLevel, nodeIds);
        }
        List<NodeVo> minNodes = nodes.get(minLevel);
        if (minNodes.size() > 1) {
            nodeIds.add(minNodes.get(0).getParent());
        }
        log.info("nodeIds: {}", nodeIds);
        return nodeIds;
    }
    private static List<Long> getSubIds(Map<Long, NodeVo> treeNodes, NodeVo currentNode, int minLevel, List<Long> subIds) {
        int currentLevel = currentNode.getLevel();
        if (currentNode.getLevel() == 1 || currentLevel < minLevel) {
            return subIds;
        }
        subIds.add(currentNode.getCurrent());
        NodeVo nodeVo = treeNodes.get(currentNode.getParent());
        subIds = getSubIds(treeNodes, nodeVo, minLevel, subIds);
        return subIds;
    }

    public static class NodeVo {

        private int level;
        private Long parent;
        private Long current;

        public NodeVo() {
        }

        public NodeVo(Long parent, Long current) {
            this.parent = parent;
            this.current = current;
        }

        public NodeVo(int level, Long parent, Long current) {
            this.level = level;
            this.parent = parent;
            this.current = current;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public Long getParent() {
            return parent;
        }

        public void setParent(Long parent) {
            this.parent = parent;
        }

        public Long getCurrent() {
            return current;
        }

        public void setCurrent(Long current) {
            this.current = current;
        }

        @Override
        public String toString() {
            return "NodeVo{" +
                    "level=" + level +
                    ", parent=" + parent +
                    ", current=" + current +
                    '}';
        }
    }
}
