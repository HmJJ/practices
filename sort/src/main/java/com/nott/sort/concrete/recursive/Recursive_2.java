package com.nott.sort.concrete.recursive;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/10 18:53
 * @Modified By:
 **/
public class Recursive_2 {

    /**
     * 443
     *      315
     *              563
     *                      564
     *
     *      314
     *              565
     */
    public static void main(String[] args) {
        Relationship tenant = new Relationship();
        PermissionVo permissionVo = getPermissionVo(tenant);
    }


    private static PermissionVo getPermissionVo(Relationship tenant) {
        List<Long> groupIds = new ArrayList<>(); // 人员所在的部门id
        PermissionVo permissionVo = new PermissionVo(tenant.getPartyIdFrom(), groupIds);
        if (groupIds.contains(tenant.getPartyIdFrom())) {
            return permissionVo;
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
            return permissionVo;
        }
        permissionVo = getSubIds(deepLevel, nodes, treeNodes);
        permissionVo.addList(groupIds);
        return permissionVo;
    }
    private static Map<Long, NodeVo> findAllNode(Relationship parent, int level, Map<Long, NodeVo> treeNodes) {
        NodeVo current = new NodeVo(level, parent.getPartyIdTo(), parent.getPartyIdFrom());
        treeNodes.put(parent.getPartyIdFrom(), current);
        level++;
        List<Relationship> children = new ArrayList<>();
        for (Relationship child : children) {
            treeNodes = findAllNode(child, level, treeNodes);
        }
        return treeNodes;
    }
    private static PermissionVo getSubIds(int deepLevel, Map<Integer, List<NodeVo>> map, Map<Long, NodeVo> treeNodes) {
        PermissionVo permissionVo;
        List<Long> permissionIds = new ArrayList<>();
        Long root = null;
        int currentLevel = deepLevel;
        List<NodeVo> currentLevelNodeVos;
        List<NodeVo> parentLevelNodeVos = new ArrayList<>();
        do {
            currentLevelNodeVos = map.get(currentLevel);
            currentLevelNodeVos.addAll(parentLevelNodeVos);
            Set<NodeVo> currentSet = new HashSet<>(currentLevelNodeVos);
            currentLevelNodeVos = new ArrayList<>(currentSet);
            parentLevelNodeVos.clear();
            for (NodeVo nodeVo : currentLevelNodeVos) {
                root = nodeVo.getCurrent();
                NodeVo parentNodeVo = treeNodes.get(nodeVo.getParent());
                permissionIds.add(nodeVo.getCurrent());
                parentLevelNodeVos.add(parentNodeVo);
            }
            currentLevel--;
            List<NodeVo> parentNodeVos = map.get(currentLevel) == null ? new ArrayList<>() : map.get(currentLevel);
            parentLevelNodeVos.addAll(parentNodeVos);
            Set<NodeVo> parentSet = new HashSet<>(parentLevelNodeVos);
            List<NodeVo> list = new ArrayList<>(parentSet);
            map.put(currentLevel, list);
        } while (parentLevelNodeVos.size() > 1);
        permissionVo = new PermissionVo(root, permissionIds);
        return permissionVo;
    }

    public static class Relationship {
        private Long id;
        /**
         * 来源partyId -
         */
        private Long partyIdFrom;
        /**
         * 去向partyTo -
         */
        private Long partyIdTo;
        /**
         * 来源角色类型编码 -
         */
        private String roleTypeFrom;
        /**
         * 去向角色类型编码 -
         */
        private String roleTypeTo;
        /**
         * 关系
         */
        private String relationshipType;
        /**
         * 开始时间 -
         */
        private LocalDateTime startTime;
        /**
         * 结束时间 -
         */
        private LocalDateTime endTime;
        /**
         * 状态 -
         */
        private String status;

        public Relationship() {
        }

        public Relationship(Long partyIdFrom, String relationshipType) {
            this.partyIdFrom = partyIdFrom;
            this.relationshipType = relationshipType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getPartyIdFrom() {
            return partyIdFrom;
        }

        public void setPartyIdFrom(Long partyIdFrom) {
            this.partyIdFrom = partyIdFrom;
        }

        public Long getPartyIdTo() {
            return partyIdTo;
        }

        public void setPartyIdTo(Long partyIdTo) {
            this.partyIdTo = partyIdTo;
        }

        public String getRoleTypeFrom() {
            return roleTypeFrom;
        }

        public void setRoleTypeFrom(String roleTypeFrom) {
            this.roleTypeFrom = roleTypeFrom;
        }

        public String getRoleTypeTo() {
            return roleTypeTo;
        }

        public void setRoleTypeTo(String roleTypeTo) {
            this.roleTypeTo = roleTypeTo;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRelationshipType() {
            return relationshipType;
        }

        public void setRelationshipType(String relationshipType) {
            this.relationshipType = relationshipType;
        }
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
    public static class PermissionVo {

        private Long root;
        private List<Long> children;

        public PermissionVo() {
        }

        public PermissionVo(Long root, List<Long> children) {
            this.root = root;
            this.children = children;
        }

        public void addList(List<Long> newList) {
            if (this.children == null) {
                this.children = new ArrayList<>();
            }
            this.children.addAll(newList);
        }

        public Long getRoot() {
            return root;
        }

        public void setRoot(Long root) {
            this.root = root;
        }

        public List<Long> getChildren() {
            return children;
        }

        public void setChildren(List<Long> children) {
            this.children = children;
        }
    }
}
