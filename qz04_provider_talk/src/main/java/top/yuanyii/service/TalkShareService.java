package top.yuanyii.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.yuanyii.dao.TalkShareDao;
import top.yuanyii.pojo.talk.TalkShare;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class TalkShareService {

	@Autowired
	private TalkShareDao talkShareDao;
	

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<TalkShare> findAll() {
		return talkShareDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TalkShare> findSearch(Map whereMap, int page, int size) {
		Specification<TalkShare> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return talkShareDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<TalkShare> findSearch(Map whereMap) {
		Specification<TalkShare> specification = createSpecification(whereMap);
		return talkShareDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public TalkShare findById(String id) {
		return talkShareDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param talkShare
	 */
	public void add(TalkShare talkShare) {
		talkShare.setTalkshareid( IdUtil.simpleUUID() +"" );
		talkShareDao.save(talkShare);
	}

	/**
	 * 修改
	 * @param talkShare
	 */
	public void update(TalkShare talkShare) {
		talkShareDao.save(talkShare);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		talkShareDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<TalkShare> createSpecification(Map searchMap) {

		return new Specification<TalkShare>() {

			@Override
			public Predicate toPredicate(Root<TalkShare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 说说转载id
                if (searchMap.get("talkshareid")!=null && !"".equals(searchMap.get("talkshareid"))) {
                	predicateList.add(cb.like(root.get("talkshareid").as(String.class), "%"+(String)searchMap.get("talkshareid")+"%"));
                }
                // 说说id
                if (searchMap.get("talkid")!=null && !"".equals(searchMap.get("talkid"))) {
                	predicateList.add(cb.like(root.get("talkid").as(String.class), "%"+(String)searchMap.get("talkid")+"%"));
                }
                // 转载说说的人
                if (searchMap.get("talkshareuseraid")!=null && !"".equals(searchMap.get("talkshareuseraid"))) {
                	predicateList.add(cb.like(root.get("talkshareuseraid").as(String.class), "%"+(String)searchMap.get("talkshareuseraid")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
