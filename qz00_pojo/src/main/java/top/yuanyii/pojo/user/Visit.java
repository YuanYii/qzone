package top.yuanyii.pojo.user;

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
@Table(name="tb_visit")
public class Visit implements Serializable{

	@Id
	private String vid;//访客自增id


	
	private String useraid;//用户A
	private String userbid;//访客B
	private java.util.Date visittime;//访问时间


}
