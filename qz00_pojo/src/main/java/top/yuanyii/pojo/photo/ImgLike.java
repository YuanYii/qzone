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
@Table(name="tb_img_like")
public class ImgLike implements Serializable{

	//照片点赞id
	@Id
	private String imglikeid;


	//照片id
	private String imgid;
	//点赞的人
	private String useraid;
	//点赞时间
	private java.util.Date imglikcreatetime;

	
}
