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
@Table(name="tb_talk_share")
public class TalkShare implements Serializable{

	@Id
	private String talkshareid;//说说转载id

	private String talkid;//说说id
	private String talkshareuseraid;//转载说说的人
	private java.util.Date talksharetime;//说说转载时间


	
}
