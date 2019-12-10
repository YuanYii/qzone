package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import top.yuanyii.pojo.user.Visit;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface VisitDao extends JpaRepository<Visit,String>,JpaSpecificationExecutor<Visit>{
	
}
