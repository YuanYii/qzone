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
@Table(name="tb_image")
public class Image implements Serializable{

	@Id
	private String imgid;//imgID


	
	private String imgsrc;//图片地址
	private String imguseraid;//作者
	private String imgdesc;//照片描述
	private java.util.Date imgcreatetime;//创建时间
	private String albumid;//所属相册
	private Integer imgstate;//照片状态


	
}
