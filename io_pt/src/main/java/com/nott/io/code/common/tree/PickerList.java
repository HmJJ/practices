package com.nott.io.code.common.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 2016/12/4.
 */
public class PickerList {

    private List<PickerNode> rootList = new ArrayList<>();

    @JsonIgnore
    private List<PickerNode> list;


    public static PickerList create(List<PickerData> list) {

        List<PickerNode> nodeList = new ArrayList<>();
        for(PickerData data : list) {
            nodeList.add(PickerNode.create(data));
        }
        for(PickerNode node1 : nodeList){
            for(PickerNode node2 : nodeList){
                if(node2.isParent(node1)){
                    node2.addChild(node1);
                    break;
                }
            }
        }
        return createRoot(nodeList);
    }

    private static PickerList createRoot(List<PickerNode> list) {

        PickerList result = new PickerList();
        for(PickerNode node : list){
            if(node.isRoot()) {
                result.rootList.add(node);
            }
        }
        result.list = list;
        return result;
    }

    private PickerNode find(String code) {
        for(PickerNode node : rootList) {
            if(node.getData().getCode().equals(code)){
                return node;
            }
        }
        return null;
    }

    public Integer findInRoot(String code, String child) {

        if(code == null || child == null) {
            return 0;
        }
        PickerNode node = find(code);
        if(CollectionUtils.isNotEmpty(node.getChildren())) {
            for(int i=0;i<node.getChildren().size();i++) {
                if(node.getChildren().get(i).getData().getCode().equals(child)){
                    return i;
                }
            }
        }
        return 0;
    }

    public Integer findInRoot(String code) {

        for(int i=0;i<rootList.size();i++) {
            if(rootList.get(i).getData().getCode().equals(code)){
                return i;
            }
        }
        return 0;
    }


    public List<PickerNode> getRootList() {
        return rootList;
    }
}
