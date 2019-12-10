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
@Table(name="tb_album_like")
public class AlbumLike implements Serializable{

	@Id
	private String albumlikeid;//相册点赞id


	
	private String albumid;//相册id
	private String useraid;//点赞的人
	private java.util.Date albumlikecreatetime;//点赞时间

}
