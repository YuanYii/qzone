package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.talk.TalkDiscuss;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface TalkDiscussDao extends JpaRepository<TalkDiscuss,String>,JpaSpecificationExecutor<TalkDiscuss>{
	
}
