package top.yuanyii.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.yuanyii.dao.TalkDao;
import top.yuanyii.pojo.talk.Talk;

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
public class TalkService {

	@Autowired
	private TalkDao talkDao;
	
//	@Autowired
//	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Talk> findAll() {
		return talkDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Talk> findSearch(Map whereMap, int page, int size) {
		Specification<Talk> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return talkDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Talk> findSearch(Map whereMap) {
		Specification<Talk> specification = createSpecification(whereMap);
		return talkDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Talk findById(String id) {
		return talkDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param talk
	 */
	public void add(Talk talk) {
		talk.setTalkid( IdUtil.simpleUUID() +"" );
		talkDao.save(talk);
	}

	/**
	 * 修改
	 * @param talk
	 */
	public void update(Talk talk) {
		talkDao.save(talk);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		talkDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Talk> createSpecification(Map searchMap) {

		return new Specification<Talk>() {

			@Override
			public Predicate toPredicate(Root<Talk> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 说说id
                if (searchMap.get("talkid")!=null && !"".equals(searchMap.get("talkid"))) {
                	predicateList.add(cb.like(root.get("talkid").as(String.class), "%"+(String)searchMap.get("talkid")+"%"));
                }
                // 说说作者
                if (searchMap.get("talkuseraid")!=null && !"".equals(searchMap.get("talkuseraid"))) {
                	predicateList.add(cb.like(root.get("talkuseraid").as(String.class), "%"+(String)searchMap.get("talkuseraid")+"%"));
                }
                // 说说内容
                if (searchMap.get("talkcontent")!=null && !"".equals(searchMap.get("talkcontent"))) {
                	predicateList.add(cb.like(root.get("talkcontent").as(String.class), "%"+(String)searchMap.get("talkcontent")+"%"));
                }
                // 图片地址1
                if (searchMap.get("img1")!=null && !"".equals(searchMap.get("img1"))) {
                	predicateList.add(cb.like(root.get("img1").as(String.class), "%"+(String)searchMap.get("img1")+"%"));
                }
                // 图片地址2
                if (searchMap.get("img2")!=null && !"".equals(searchMap.get("img2"))) {
                	predicateList.add(cb.like(root.get("img2").as(String.class), "%"+(String)searchMap.get("img2")+"%"));
                }
                // 图片地址2
                if (searchMap.get("img3")!=null && !"".equals(searchMap.get("img3"))) {
                	predicateList.add(cb.like(root.get("img3").as(String.class), "%"+(String)searchMap.get("img3")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
