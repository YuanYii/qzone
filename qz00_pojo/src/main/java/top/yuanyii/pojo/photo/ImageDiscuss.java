package top.yuanyii.pojo.photo;

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
@Table(name="tb_image_discuss")
public class ImageDiscuss implements Serializable{

	@Id
	private String imagediscussid;//照片评论id


	
	private String discussfatherid;//评论父id
	private String imgid;//照片id
	private String discussuser;//评论人
	private String discusscontent;//评论内容
	private java.util.Date discusstime;//评论时间
	private Integer discussstate;//评论状态

	
}
