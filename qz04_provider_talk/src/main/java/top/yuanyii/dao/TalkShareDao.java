package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import top.yuanyii.pojo.talk.TalkShare;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface TalkShareDao extends JpaRepository<TalkShare,String>,JpaSpecificationExecutor<TalkShare>{
	
}
