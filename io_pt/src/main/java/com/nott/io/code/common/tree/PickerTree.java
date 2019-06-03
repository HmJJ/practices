package com.nott.io.code.common.tree;


import com.wayonsys.common.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 2016/10/28.
 */
public class PickerTree {

    private PickerNode root;

    public static PickerTree createByTreeData(List<TreeData> list) {
        List<PickerNode> referenceNodeList = new ArrayList<>();
        for(TreeData data : list) {
            String parent = StringHelper.getString(data.getParentId());
            PickerData pickerData = new PickerData(data.getId().toString(), parent, data.getLabel(), data.getLevel());
            referenceNodeList.add(PickerNode.create(pickerData));
        }
        for(PickerNode node1 : referenceNodeList){
            for(PickerNode node2 : referenceNodeList){
                if(node2.isParent(node1)){
                    node2.addChild(node1);
                    break;
                }
            }
        }
        return createRoot(referenceNodeList);
    }

    public static PickerTree create(List<PickerData> list) {
        List<PickerNode> referenceNodeList = new ArrayList<>();
        for(PickerData data : list) {
            referenceNodeList.add(PickerNode.create(data));
        }
        for(PickerNode node1 : referenceNodeList){
            for(PickerNode node2 : referenceNodeList){
                if(node2.isParent(node1)){
                    node2.addChild(node1);
                    break;
                }
            }
        }
        return createRoot(referenceNodeList);
    }

    private static PickerTree createRoot(List<PickerNode> list) {

        PickerTree result = new PickerTree();
        for(PickerNode node : list){
            if(node.isRoot()) {
                result.root = node;
                break;
            }
        }
        return result;
    }


    public PickerNode getRoot() {
        return root;
    }
}
