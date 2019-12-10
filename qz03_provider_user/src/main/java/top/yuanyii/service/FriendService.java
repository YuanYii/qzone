package top.yuanyii.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import top.yuanyii.dao.FriendDao;
import top.yuanyii.pojo.user.Friend;
import top.yuanyii.util.IdWorker;


/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	
	@Resource
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Friend> findAll() {
		return friendDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Friend> findSearch(Map whereMap, int page, int size) {
		Specification<Friend> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return friendDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Friend> findSearch(Map whereMap) {
		Specification<Friend> specification = createSpecification(whereMap);
		return friendDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Friend findById(String id) {
		return friendDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param friend
	 */
	public void add(Friend friend) {
		friend.setFriendid( idWorker.nextId()+"" );
		friendDao.save(friend);
	}

	/**
	 * 修改
	 * @param friend
	 */
	public void update(Friend friend) {
		friendDao.save(friend);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		friendDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Friend> createSpecification(Map searchMap) {

		return new Specification<Friend>() {

			@Override
			public Predicate toPredicate(Root<Friend> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 好友自增id
                if (searchMap.get("friendid")!=null && !"".equals(searchMap.get("friendid"))) {
                	predicateList.add(cb.like(root.get("friendid").as(String.class), "%"+(String)searchMap.get("friendid")+"%"));
                }
                // 自己
                if (searchMap.get("useraid")!=null && !"".equals(searchMap.get("useraid"))) {
                	predicateList.add(cb.like(root.get("useraid").as(String.class), "%"+(String)searchMap.get("useraid")+"%"));
                }
                // 好友id
                if (searchMap.get("userbid")!=null && !"".equals(searchMap.get("userbid"))) {
                	predicateList.add(cb.like(root.get("userbid").as(String.class), "%"+(String)searchMap.get("userbid")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
