package com.veeker.core.basics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：qiaoliang
 */
public class TreeNode {

    protected Long id;
    protected Long parentId;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public void add(TreeNode node) {
        children.add(node);
    }

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

}
