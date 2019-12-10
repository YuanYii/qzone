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

import top.yuanyii.dao.ImageVisitDao;
import top.yuanyii.pojo.photo.ImageVisit;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ImageVisitService {

	@Autowired
	private ImageVisitDao imageVisitDao;
	
	/**
	 * 查询全部列表
	 * @return
	 */
	public List<ImageVisit> findAll() {
		return imageVisitDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ImageVisit> findSearch(Map whereMap, int page, int size) {
		Specification<ImageVisit> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return imageVisitDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<ImageVisit> findSearch(Map whereMap) {
		Specification<ImageVisit> specification = createSpecification(whereMap);
		return imageVisitDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ImageVisit findById(String id) {
		return imageVisitDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param imageVisit
	 */
	public void add(ImageVisit imageVisit) {
		imageVisit.setVisitid( IdUtil.simpleUUID() +"" );
		imageVisitDao.save(imageVisit);
	}

	/**
	 * 修改
	 * @param imageVisit
	 */
	public void update(ImageVisit imageVisit) {
		imageVisitDao.save(imageVisit);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		imageVisitDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<ImageVisit> createSpecification(Map searchMap) {

		return new Specification<ImageVisit>() {

			@Override
			public Predicate toPredicate(Root<ImageVisit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 照片浏览id
                if (searchMap.get("visitid")!=null && !"".equals(searchMap.get("visitid"))) {
                	predicateList.add(cb.like(root.get("visitid").as(String.class), "%"+(String)searchMap.get("visitid")+"%"));
                }
                // 照片id
                if (searchMap.get("imgid")!=null && !"".equals(searchMap.get("imgid"))) {
                	predicateList.add(cb.like(root.get("imgid").as(String.class), "%"+(String)searchMap.get("imgid")+"%"));
                }
                // 谁来看
                if (searchMap.get("useraid")!=null && !"".equals(searchMap.get("useraid"))) {
                	predicateList.add(cb.like(root.get("useraid").as(String.class), "%"+(String)searchMap.get("useraid")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
