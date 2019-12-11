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
@Table(name="tb_message_discuss")
public class MessageDiscuss implements Serializable{

	@Id
	private String messagediscussid;//评论id


	
	private String messagediscussfatherid;//评论父id
	private String messageid;//留言id
	private String useraid;//评论人
	private String discusscontent;//评论内容
	private java.util.Date discusstime;//评论时间
	private Integer discussstate;//评论状态

	
	public String getMessagediscussid() {		
		return messagediscussid;
	}
	public void setMessagediscussid(String messagediscussid) {
		this.messagediscussid = messagediscussid;
	}

	public String getMessagediscussfatherid() {		
		return messagediscussfatherid;
	}
	public void setMessagediscussfatherid(String messagediscussfatherid) {
		this.messagediscussfatherid = messagediscussfatherid;
	}

	public String getMessageid() {		
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getUseraid() {		
		return useraid;
	}
	public void setUseraid(String useraid) {
		this.useraid = useraid;
	}

	public String getDiscusscontent() {		
		return discusscontent;
	}
	public void setDiscusscontent(String discusscontent) {
		this.discusscontent = discusscontent;
	}

	public java.util.Date getDiscusstime() {		
		return discusstime;
	}
	public void setDiscusstime(java.util.Date discusstime) {
		this.discusstime = discusstime;
	}

	public Integer getDiscussstate() {		
		return discussstate;
	}
	public void setDiscussstate(Integer discussstate) {
		this.discussstate = discussstate;
	}


	
}
