package com.nott.io.code.common.tree;

import java.util.*;

/**
 * Created by allen on 2017/2/15.
 */
public class TreeData {

    private String label;
    private String name;
    private Long id;
    private Long parentId;
    private String code;
    private String groupType;
    private Integer level = 1;

    private String parentCode;
    private Integer rank;


    public static List<TreeData> removeDuplicate(List<TreeData> list) {
        List<TreeData> results = new ArrayList<>();
        Set<TreeData> set = new HashSet<>();
        set.addAll(list);
        results.addAll(set);
        return results;
    }

    public TreeData() {
    }

    public TreeData(Long id, String label, Long parentId) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }

    public TreeData(Long id, String name) {
        this.id = id;
        this.name = name;
        this.level = 1;
    }

    public TreeData(Long id, String label, String parentCode, String code, Integer level) {
        this.label = label;
        this.id = id;
        this.parentCode = parentCode;
        this.code = code;
        this.level = level;
    }

    public TreeData(Long id, String label, Long parentId, String code) {
        this.label = label;
        this.id = id;
        this.parentId = parentId;
        this.code = code;
    }

    public TreeData(Long id, String label, Long parentId, String code,String groupType, Integer level) {
        this.label = label;
        this.id = id;
        this.parentId = parentId;
        this.code = code;
        this.groupType = groupType;
        this.level = level;
    }

    public TreeData(Long id, String label, Long parentId, String code,String groupType, Integer level,Integer rank) {
        this.label = label;
        this.id = id;
        this.parentId = parentId;
        this.code = code;
        this.groupType = groupType;
        this.level = level;
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeData treeData = (TreeData) o;
        if (treeData.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, treeData.id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
