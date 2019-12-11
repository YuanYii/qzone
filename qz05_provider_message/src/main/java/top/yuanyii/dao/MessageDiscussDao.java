package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.message.MessageDiscuss;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface MessageDiscussDao extends JpaRepository<MessageDiscuss,String>,JpaSpecificationExecutor<MessageDiscuss>{
	
}
