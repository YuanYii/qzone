package top.yuanyii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yuanyii.pojo.photo.Album;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AlbumDao extends JpaRepository<Album,String>,JpaSpecificationExecutor<Album>{
	
}
