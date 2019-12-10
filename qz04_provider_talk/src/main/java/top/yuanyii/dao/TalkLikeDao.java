package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.talk.TalkLike;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface TalkLikeDao extends JpaRepository<TalkLike,String>,JpaSpecificationExecutor<TalkLike>{
	
}
