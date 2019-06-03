package com.nott.io.code.common.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.collections4.CollectionUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 2017/2/15.
 */
public class TreeNode {

    private String label;
    private Long id;
    private Long parentId;
    private String parentCode;
    private String code;
    private String groupType;
    private List<TreeNode> children;

    public static TreeNode create(TreeData data) {

        TreeNode node = new TreeNode();
        node.id = data.getId();
        node.parentId = data.getParentId();
        node.parentCode = data.getParentCode();
        node.label = data.getLabel();
        node.code = data.getCode();
        node.groupType = data.getGroupType();
        return node;
    }

    public static TreeNode createEmpty() {
        return new TreeNode();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return id == null;
    }

    @JsonIgnore
    public boolean hasChildren() {
        return CollectionUtils.isNotEmpty(children);
    }

    @JsonIgnore
    public boolean hasParent() {
        return parentId != null;
    }

    @JsonIgnore
    public boolean isRoot() {
        return parentId == null;
    }

    public boolean isParent(TreeNode node) {
        if (node.getParentCode() != null && code != null) {
            return id.equals(node.getParentId()) || code.equals(node.getParentCode());
        }
        return id.equals(node.getParentId());
    }

    public void addChild(TreeNode node) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
