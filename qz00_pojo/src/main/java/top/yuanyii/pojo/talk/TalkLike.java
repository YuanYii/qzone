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
@Table(name="tb_talk_like")
public class TalkLike implements Serializable{

	@Id
	private String talklikeid;//说说点赞id
	private String talkid;//说说id
	private String useraid;//点赞的人
	private java.util.Date talkliketime;//点赞时间


}
