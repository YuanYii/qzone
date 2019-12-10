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

import top.yuanyii.dao.ImgLikeDao;
import top.yuanyii.pojo.photo.ImgLike;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ImgLikeService {

	@Autowired
	private ImgLikeDao imgLikeDao;
	
	/**
	 * 查询全部列表
	 * @return
	 */
	public List<ImgLike> findAll() {
		return imgLikeDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ImgLike> findSearch(Map whereMap, int page, int size) {
		Specification<ImgLike> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return imgLikeDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<ImgLike> findSearch(Map whereMap) {
		Specification<ImgLike> specification = createSpecification(whereMap);
		return imgLikeDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ImgLike findById(String id) {
		return imgLikeDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param imgLike
	 */
	public void add(ImgLike imgLike) {
		imgLike.setImglikeid( IdUtil.simpleUUID() +"" );
		imgLikeDao.save(imgLike);
	}

	/**
	 * 修改
	 * @param imgLike
	 */
	public void update(ImgLike imgLike) {
		imgLikeDao.save(imgLike);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		imgLikeDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<ImgLike> createSpecification(Map searchMap) {

		return new Specification<ImgLike>() {

			@Override
			public Predicate toPredicate(Root<ImgLike> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 照片点赞id
                if (searchMap.get("imglikeid")!=null && !"".equals(searchMap.get("imglikeid"))) {
                	predicateList.add(cb.like(root.get("imglikeid").as(String.class), "%"+(String)searchMap.get("imglikeid")+"%"));
                }
                // 照片id
                if (searchMap.get("imgid")!=null && !"".equals(searchMap.get("imgid"))) {
                	predicateList.add(cb.like(root.get("imgid").as(String.class), "%"+(String)searchMap.get("imgid")+"%"));
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
