package com.nott.io.code.common.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 2016/10/28.
 */
public class PickerNode {

    private PickerData data;
    private List<PickerNode> children;


    public static PickerNode create(PickerData data) {

        PickerNode node = new PickerNode();
        node.data = data;
        return node;
    }

    @JsonIgnore
    public boolean isRoot() {
        return StringUtils.isBlank(data.getParentCode());
    }

    public boolean isParent(PickerNode node) {
        return data.getCode().equals(node.getData().getParentCode());
    }

    public void addChild(PickerNode node) {
        if(children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }

//    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonInclude( JsonInclude.Include.NON_NULL)
    public List<PickerNode> getChildren() {
        return children;
    }

    public void setChildren(List<PickerNode> children) {
        this.children = children;
    }

    public PickerData getData() {
        return data;
    }

    public void setData(PickerData data) {
        this.data = data;
    }
}
