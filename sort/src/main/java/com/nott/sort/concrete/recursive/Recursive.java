package com.nott.sort.concrete.recursive;

import java.util.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/9 11:01
 * @Modified By:
 **/
public class Recursive {

    public static void main(String[] args) {
        List<Long> groupIdList = new ArrayList();
        List<Long> currentGroupList = new ArrayList<>();
        currentGroupList.add(443L);
        List<TreeData> treeDataList = new ArrayList<>();
        treeDataList.add(new TreeData(null, 443L));
        treeDataList.add(new TreeData(443L, 314L));
        treeDataList.add(new TreeData(443L, 315L));
        treeDataList.add(new TreeData(443L, 316L));
        treeDataList.add(new TreeData(443L, 317L));
        treeDataList.add(new TreeData(443L, 342L));
        treeDataList.add(new TreeData(443L, 343L));
        treeDataList.add(new TreeData(443L, 344L));
        treeDataList.add(new TreeData(443L, 501L));
        treeDataList.add(new TreeData(443L, 503L));
        treeDataList.add(new TreeData(314L, 517L));
        treeDataList.add(new TreeData(314L, 518L));
        treeDataList.add(new TreeData(null, 520L));
        treeDataList.add(new TreeData(null, 521L));
        treeDataList.add(new TreeData(null, 522L));
        treeDataList.add(new TreeData(null, 523L));
        treeDataList.add(new TreeData(null, 528L));
        treeDataList.add(new TreeData(null, 530L));
        treeDataList.add(new TreeData(443L, 539L));
        List<Long> subGroupIdList = getSubGroups(treeDataList, currentGroupList);
        groupIdList.addAll(currentGroupList);
        groupIdList.addAll(subGroupIdList);
        groupIdList = removeDuplicate(groupIdList);
        System.out.println(groupIdList);
    }

    private static List<Long> getSubGroups(List<TreeData> dataList, List<Long> currentGroupList) {
        List<Long> groupIdList = new ArrayList();
        Iterator var4 = dataList.iterator();

        while(var4.hasNext()) {
            TreeData treeData = (TreeData)var4.next();
            TreeData data = getSub(treeData, dataList, currentGroupList);
            if (data != null) {
                groupIdList.add(data.getId());
                currentGroupList.add(data.getId());
            }
        }

        return groupIdList;
    }

    private static TreeData getSub(TreeData treeData, List<TreeData> dataList, List<Long> currentGroupList) {
        if (isSubGroup(treeData, currentGroupList)) {
            return treeData;
        } else {
            for(TreeData parent = getParentRelationship(treeData, dataList); parent != null; parent = getParentRelationship(parent, dataList)) {
                if (isSubGroup(parent, currentGroupList)) {
                    return parent;
                }
            }

            return null;
        }
    }

    private static boolean isSubGroup(TreeData data, List<Long> currentGroupList) {
        return currentGroupList.contains(data.getParentId());
    }

    private static TreeData getParentRelationship(TreeData data, List<TreeData> dataList) {
        Iterator var3 = dataList.iterator();

        TreeData treeData;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            treeData = (TreeData)var3.next();
        } while(!treeData.getId().equals(data.getParentId()));

        return treeData;
    }

    public static <T> List<T> removeDuplicate(List<T> list) {
        List<T> result = new ArrayList();
        Set<T> set = new HashSet();
        set.addAll(list);
        result.addAll(set);
        return result;
    }

    public static class TreeData {
        private Long id;
        private Long parentId;

        public TreeData(Long parentId, Long id) {
            this.id = id;
            this.parentId = parentId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return "TreeData{" +
                    "id=" + id +
                    ", parentId=" + parentId +
                    '}';
        }
    }

}
