package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.photo.AlbumLike;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AlbumLikeDao extends JpaRepository<AlbumLike,String>,JpaSpecificationExecutor<AlbumLike>{
	
}
