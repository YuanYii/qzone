package top.yuanyii.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.yuanyii.dao.TalkDiscussDao;
import top.yuanyii.pojo.talk.TalkDiscuss;

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
public class TalkDiscussService {

	@Autowired
	private TalkDiscussDao talkDiscussDao;
	

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<TalkDiscuss> findAll() {
		return talkDiscussDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TalkDiscuss> findSearch(Map whereMap, int page, int size) {
		Specification<TalkDiscuss> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return talkDiscussDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<TalkDiscuss> findSearch(Map whereMap) {
		Specification<TalkDiscuss> specification = createSpecification(whereMap);
		return talkDiscussDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public TalkDiscuss findById(String id) {
		return talkDiscussDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param talkDiscuss
	 */
	public void add(TalkDiscuss talkDiscuss) {
		talkDiscuss.setTalkdiscussid( IdUtil.simpleUUID() +""  );
		talkDiscussDao.save(talkDiscuss);
	}

	/**
	 * 修改
	 * @param talkDiscuss
	 */
	public void update(TalkDiscuss talkDiscuss) {
		talkDiscussDao.save(talkDiscuss);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		talkDiscussDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<TalkDiscuss> createSpecification(Map searchMap) {

		return new Specification<TalkDiscuss>() {

			@Override
			public Predicate toPredicate(Root<TalkDiscuss> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 说说评论id
                if (searchMap.get("talkdiscussid")!=null && !"".equals(searchMap.get("talkdiscussid"))) {
                	predicateList.add(cb.like(root.get("talkdiscussid").as(String.class), "%"+(String)searchMap.get("talkdiscussid")+"%"));
                }
                // 说说评论父id
                if (searchMap.get("talkdiscussfatherid")!=null && !"".equals(searchMap.get("talkdiscussfatherid"))) {
                	predicateList.add(cb.like(root.get("talkdiscussfatherid").as(String.class), "%"+(String)searchMap.get("talkdiscussfatherid")+"%"));
                }
                // 说说id
                if (searchMap.get("talkid")!=null && !"".equals(searchMap.get("talkid"))) {
                	predicateList.add(cb.like(root.get("talkid").as(String.class), "%"+(String)searchMap.get("talkid")+"%"));
                }
                // 说说评论人
                if (searchMap.get("discussuseraid")!=null && !"".equals(searchMap.get("discussuseraid"))) {
                	predicateList.add(cb.like(root.get("discussuseraid").as(String.class), "%"+(String)searchMap.get("discussuseraid")+"%"));
                }
                // 说说评论内容
                if (searchMap.get("discusscontent")!=null && !"".equals(searchMap.get("discusscontent"))) {
                	predicateList.add(cb.like(root.get("discusscontent").as(String.class), "%"+(String)searchMap.get("discusscontent")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
