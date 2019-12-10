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
@Table(name="tb_friend")
public class Friend implements Serializable{

	@Id
	private String friendid;//好友自增id


	
	private String useraid;//自己
	private String userbid;//好友id
	private Integer friendstate;//好友状态

	


	
}
