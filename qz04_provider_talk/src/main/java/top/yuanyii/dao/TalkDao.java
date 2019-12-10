package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.talk.Talk;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface TalkDao extends JpaRepository<Talk,String>,JpaSpecificationExecutor<Talk>{
	
}
