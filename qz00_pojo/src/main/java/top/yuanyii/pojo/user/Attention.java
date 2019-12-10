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
@Table(name="tb_attention")
public class Attention implements Serializable{

	@Id
	private String attentionid;//关注id

	private String useraid;//用户
	private String userbid;//被关注的用户
	private java.util.Date attentiontime;//关注时间

	


	
}
