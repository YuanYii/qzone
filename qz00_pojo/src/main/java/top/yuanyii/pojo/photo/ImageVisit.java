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
@Table(name="tb_image_visit")
public class ImageVisit implements Serializable{

	@Id
	private String visitid;//照片浏览id


	
	private String imgid;//照片id
	private String useraid;//谁来看
	private java.util.Date visittime;//查看时间


}
