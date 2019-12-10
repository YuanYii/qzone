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
@Table(name="tb_user")
public class User implements Serializable{

	@Id
	private String uid;//用户id


	
	private String uname;//用户名
	private String upass;//用户密码
	private String usalt;//密码盐值
	private String uemail;//用户邮箱
	private java.util.Date ucreatetime;//注册时间
	private String urealname;//真实姓名
	private String uphone;//用户手机号
	private String uimg;//头像id
	private Integer ustate;//用户状态

}
