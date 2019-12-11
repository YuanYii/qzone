package top.yuanyii.pojo.message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_message")
public class Message implements Serializable{

	@Id
	private String messageid;//留言id


	
	private String messageuseraid;//留言作者
	private String messageuserbid;//被留言的人
	private String messagecontent;//留言内容
	private java.util.Date messagetime;//留言时间
	private Integer messagestate;//留言状态

	
	public String getMessageid() {		
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getMessageuseraid() {		
		return messageuseraid;
	}
	public void setMessageuseraid(String messageuseraid) {
		this.messageuseraid = messageuseraid;
	}

	public String getMessageuserbid() {		
		return messageuserbid;
	}
	public void setMessageuserbid(String messageuserbid) {
		this.messageuserbid = messageuserbid;
	}

	public String getMessagecontent() {		
		return messagecontent;
	}
	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	public java.util.Date getMessagetime() {		
		return messagetime;
	}
	public void setMessagetime(java.util.Date messagetime) {
		this.messagetime = messagetime;
	}

	public Integer getMessagestate() {		
		return messagestate;
	}
	public void setMessagestate(Integer messagestate) {
		this.messagestate = messagestate;
	}


	
}
