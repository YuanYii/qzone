package top.yuanyii.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import top.yuanyii.dao.VisitDao;
import top.yuanyii.pojo.user.Visit;
/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class VisitService {

	@Autowired
	private VisitDao visitDao;
	


	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Visit> findAll() {
		return visitDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Visit> findSearch(Map whereMap, int page, int size) {
		Specification<Visit> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return visitDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Visit> findSearch(Map whereMap) {
		Specification<Visit> specification = createSpecification(whereMap);
		return visitDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Visit findById(String id) {
		return visitDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param visit
	 */
	public void add(Visit visit) {
		visit.setVid(IdUtil.simpleUUID()+"" );
		visitDao.save(visit);
	}

	/**
	 * 修改
	 * @param visit
	 */
	public void update(Visit visit) {
		visitDao.save(visit);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		visitDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Visit> createSpecification(Map searchMap) {

		return new Specification<Visit>() {

			@Override
			public Predicate toPredicate(Root<Visit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 访客自增id
                if (searchMap.get("vid")!=null && !"".equals(searchMap.get("vid"))) {
                	predicateList.add(cb.like(root.get("vid").as(String.class), "%"+(String)searchMap.get("vid")+"%"));
                }
                // 用户A
                if (searchMap.get("useraid")!=null && !"".equals(searchMap.get("useraid"))) {
                	predicateList.add(cb.like(root.get("useraid").as(String.class), "%"+(String)searchMap.get("useraid")+"%"));
                }
                // 访客B
                if (searchMap.get("userbid")!=null && !"".equals(searchMap.get("userbid"))) {
                	predicateList.add(cb.like(root.get("userbid").as(String.class), "%"+(String)searchMap.get("userbid")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
