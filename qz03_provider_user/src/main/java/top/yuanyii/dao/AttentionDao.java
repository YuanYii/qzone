package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import top.yuanyii.pojo.user.Attention;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AttentionDao extends JpaRepository<Attention,String>,JpaSpecificationExecutor<Attention>{
	
}
