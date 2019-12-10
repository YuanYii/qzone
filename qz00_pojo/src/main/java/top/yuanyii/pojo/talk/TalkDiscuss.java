package top.yuanyii.pojo.talk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_talk_discuss")
public class TalkDiscuss implements Serializable{

	@Id
	private String talkdiscussid;//说说评论id
	private String talkdiscussfatherid;//说说评论父id
	private String talkid;//说说id
	private String discussuseraid;//说说评论人
	private String discusscontent;//说说评论内容
	private java.util.Date discusstime;//说说评论时间
	private Integer discussstate;//说说评论状态


	
}
