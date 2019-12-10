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
@Table(name="tb_album")
public class Album implements Serializable{

	@Id
	private String albumid;//


	
	private String albumname;//相册名
	private String albumdesc;//相册描述
	private String albumaddress;//相册地址
	private Integer albumavaible;//相册状态
	private String albumclassify;//相册分类
	private Integer albumorderid;//相册排序


	
}
