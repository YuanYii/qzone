package top.yuanyii.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 闫佳兴
 * @data 2019/11/16 17:34
 * @desc 构建菜单层级关系
 */
public class TreeNodeBuilder {



    /**
     * 把没有层级关系的集合变成有层级关系的
     * @param treeNodes 给定一个树
     * @param talkdiscussfatherid 父节点id
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer talkdiscussfatherid){
        List<TreeNode> nodes=new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if(n1.getTalkdiscussfatherid()==talkdiscussfatherid) {
                nodes.add(n1);
            }
            for (TreeNode n2 : treeNodes) {
                if(n1.getTalkdiscussid()==n2.getTalkdiscussfatherid()) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
