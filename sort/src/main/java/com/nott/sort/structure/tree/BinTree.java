/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/18 11:16
 * @Modified By:
 */
package com.nott.sort.structure.tree;

public class BinTree {

    private int value;
    private BinTree node;
    private BinTree left;
    private BinTree right;

    public BinTree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void add(int i) {
        if (i < this.value) {
            if (this.left == null) {
                this.left = new BinTree(i);
            } else {
                this.left.add(i);
            }
        } else {
            if (this.right == null) {
                this.right = new BinTree(i);
            } else {
                this.right.add(i);
            }
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinTree getNode() {
        return node;
    }

    public void setNode(BinTree node) {
        this.node = node;
    }

    public BinTree getLeft() {
        return left;
    }

    public void setLeft(BinTree left) {
        this.left = left;
    }

    public BinTree getRight() {
        return right;
    }

    public void setRight(BinTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return this.value + " ";
    }
}
