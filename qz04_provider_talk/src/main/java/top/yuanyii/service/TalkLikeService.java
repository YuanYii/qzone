package top.yuanyii.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.yuanyii.dao.TalkLikeDao;
import top.yuanyii.pojo.talk.TalkLike;

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
public class TalkLikeService {

	@Autowired
	private TalkLikeDao talkLikeDao;
	
//	@Autowired
//	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<TalkLike> findAll() {
		return talkLikeDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TalkLike> findSearch(Map whereMap, int page, int size) {
		Specification<TalkLike> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return talkLikeDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<TalkLike> findSearch(Map whereMap) {
		Specification<TalkLike> specification = createSpecification(whereMap);
		return talkLikeDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public TalkLike findById(String id) {
		return talkLikeDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param talkLike
	 */
	public void add(TalkLike talkLike) {
		talkLike.setTalklikeid( IdUtil.simpleUUID() +"" );
		talkLikeDao.save(talkLike);
	}

	/**
	 * 修改
	 * @param talkLike
	 */
	public void update(TalkLike talkLike) {
		talkLikeDao.save(talkLike);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		talkLikeDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<TalkLike> createSpecification(Map searchMap) {

		return new Specification<TalkLike>() {

			@Override
			public Predicate toPredicate(Root<TalkLike> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 说说点赞id
                if (searchMap.get("talklikeid")!=null && !"".equals(searchMap.get("talklikeid"))) {
                	predicateList.add(cb.like(root.get("talklikeid").as(String.class), "%"+(String)searchMap.get("talklikeid")+"%"));
                }
                // 说说id
                if (searchMap.get("talkid")!=null && !"".equals(searchMap.get("talkid"))) {
                	predicateList.add(cb.like(root.get("talkid").as(String.class), "%"+(String)searchMap.get("talkid")+"%"));
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
