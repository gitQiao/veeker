package com.veeker.core.utils;

import com.veeker.core.basics.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ：qiaoliang
 */
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @author ：qiaoliang
     * @param treeNodes :   传入的树节点列表
     * @param root : 顶级ID
     * @return java.util.List<T>
     */
    public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Long root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getParentId().equals(treeNode.getId())) {
                    if (Objects.isNull(treeNode.getChildren())) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @author ：qiaoliang
     * @param treeNodes : 传入的树节点列表
     * @param root : 顶级ID
     * @return java.util.List<T>
     */
    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Long root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    public static <T extends TreeNode,V extends TreeNode> List<T> buildByRecursiveMultiple(List<T> trees, List<V> treeNodes, Long root) {
        List<T> treesList = new ArrayList<T>();
        List<Long> collect = treeNodes.stream().map(V::getParentId).collect(Collectors.toList());
        for (T tree : trees) {
            if (root.equals(tree.getParentId())) {
                T children = findChildren(tree, trees);
                if(collect.contains(tree.getId())){
                    findChildrenMultiple(tree, treeNodes);
                }
                treesList.add(children);
            }
        }
        return treesList;
    }

    /**
     * 递归查找子节点
     *
     * @author ：qiaoliang
     * @param treeNode : 子节点
     * @param treeNodes : 传入的树节点列表
     * @return T
     */
    public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (Objects.isNull(treeNode.getChildren())) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    public static <T extends TreeNode,V extends TreeNode> T findChildrenMultiple(T treeNode, List<V> treeNodes) {
        for (V it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (Objects.isNull(treeNode.getChildren())) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildrenMultiple(it, treeNodes));
            }
        }
        return treeNode;
    }

}
