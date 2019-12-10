package top.yuanyii.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.yuanyii.dao.AlbumLikeDao;
import top.yuanyii.pojo.photo.AlbumLike;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class AlbumLikeService {

	@Autowired
	private AlbumLikeDao albumLikeDao;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<AlbumLike> findAll() {
		return albumLikeDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<AlbumLike> findSearch(Map whereMap, int page, int size) {
		Specification<AlbumLike> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return albumLikeDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<AlbumLike> findSearch(Map whereMap) {
		Specification<AlbumLike> specification = createSpecification(whereMap);
		return albumLikeDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public AlbumLike findById(String id) {
		return albumLikeDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param albumLike
	 */
	public void add(AlbumLike albumLike) {
		albumLike.setAlbumid( IdUtil.simpleUUID()+"" );
		albumLikeDao.save(albumLike);
	}

	/**
	 * 修改
	 * @param albumLike
	 */
	public void update(AlbumLike albumLike) {
		albumLikeDao.save(albumLike);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		albumLikeDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<AlbumLike> createSpecification(Map searchMap) {

		return new Specification<AlbumLike>() {

			@Override
			public Predicate toPredicate(Root<AlbumLike> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 相册点赞id
                if (searchMap.get("albumlikeid")!=null && !"".equals(searchMap.get("albumlikeid"))) {
                	predicateList.add(cb.like(root.get("albumlikeid").as(String.class), "%"+(String)searchMap.get("albumlikeid")+"%"));
                }
                // 相册id
                if (searchMap.get("albumid")!=null && !"".equals(searchMap.get("albumid"))) {
                	predicateList.add(cb.like(root.get("albumid").as(String.class), "%"+(String)searchMap.get("albumid")+"%"));
                }
                // 点赞的人
                if (searchMap.get("useraid")!=null && !"".equals(searchMap.get("useraid"))) {
                	predicateList.add(cb.like(root.get("useraid").as(String.class), "%"+(String)searchMap.get("useraid")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
