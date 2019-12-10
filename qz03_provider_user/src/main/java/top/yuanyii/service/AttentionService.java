package top.yuanyii.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import top.yuanyii.dao.AttentionDao;
import top.yuanyii.pojo.user.Attention;


/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class AttentionService {

	@Autowired
	private AttentionDao attentionDao;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Attention> findAll() {
		return attentionDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Attention> findSearch(Map whereMap, int page, int size) {
		Specification<Attention> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return attentionDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Attention> findSearch(Map whereMap) {
		Specification<Attention> specification = createSpecification(whereMap);
		return attentionDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Attention findById(String id) {
		return attentionDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param attention
	 */
	public void add(Attention attention) {
		attention.setAttentionid(IdUtil.simpleUUID()+"" );
		attentionDao.save(attention);
	}



	/**
	 * 修改
	 * @param attention
	 */
	public void update(Attention attention) {
		attentionDao.save(attention);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		attentionDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Attention> createSpecification(Map searchMap) {

		return new Specification<Attention>() {

			@Override
			public Predicate toPredicate(Root<Attention> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 关注id
                if (searchMap.get("attentionid")!=null && !"".equals(searchMap.get("attentionid"))) {
                	predicateList.add(cb.like(root.get("attentionid").as(String.class), "%"+(String)searchMap.get("attentionid")+"%"));
                }
                // 用户
                if (searchMap.get("useraid")!=null && !"".equals(searchMap.get("useraid"))) {
                	predicateList.add(cb.like(root.get("useraid").as(String.class), "%"+(String)searchMap.get("useraid")+"%"));
                }
                // 被关注的用户
                if (searchMap.get("userbid")!=null && !"".equals(searchMap.get("userbid"))) {
                	predicateList.add(cb.like(root.get("userbid").as(String.class), "%"+(String)searchMap.get("userbid")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
