package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.photo.ImageDiscuss;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ImageDiscussDao extends JpaRepository<ImageDiscuss,String>,JpaSpecificationExecutor<ImageDiscuss>{
	
}
