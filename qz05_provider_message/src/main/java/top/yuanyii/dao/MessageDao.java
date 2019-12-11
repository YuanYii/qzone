package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import top.yuanyii.pojo.message.Message;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface MessageDao extends JpaRepository<Message,String>,JpaSpecificationExecutor<Message>{
	
}
