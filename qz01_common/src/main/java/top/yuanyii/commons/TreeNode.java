package top.yuanyii.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

	private Integer talkdiscussid;
	private Integer talkdiscussfatherid;
	private Integer talkid;
	private String discussuser;
	private String discusscontent;
	private Data discusstime;
	private Integer discussstate;
	private Integer spread;

	private List<TreeNode> children = new ArrayList<TreeNode>();

}
