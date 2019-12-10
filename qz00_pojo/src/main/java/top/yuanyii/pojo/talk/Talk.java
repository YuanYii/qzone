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
@Table(name="tb_talk")
public class Talk implements Serializable{

	@Id
	private String talkid;//说说id


	
	private String talkuseraid;//说说作者
	private String talkcontent;//说说内容
	private java.util.Date talktime;//发布时间
	private Integer talkstate;//说说状态
	private String img1;//图片地址1
	private String img2;//图片地址2
	private String img3;//图片地址2

	

	
}
