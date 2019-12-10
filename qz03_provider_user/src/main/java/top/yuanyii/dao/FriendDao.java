package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.user.Friend;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface FriendDao extends JpaRepository<Friend,String>,JpaSpecificationExecutor<Friend>{
	
}
