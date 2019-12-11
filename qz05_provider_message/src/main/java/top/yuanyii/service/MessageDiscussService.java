package top.yuanyii.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

import top.yuanyii.dao.MessageDiscussDao;
import top.yuanyii.pojo.message.MessageDiscuss;

/**
 * 服务层
 *
 * @author Administrator
 *
 */
@Service
public class MessageDiscussService {

	@Autowired
	private MessageDiscussDao messageDiscussDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public List<MessageDiscuss> findAll() {
		return messageDiscussDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<MessageDiscuss> findSearch(Map whereMap, int page, int size) {
		Specification<MessageDiscuss> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return messageDiscussDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<MessageDiscuss> findSearch(Map whereMap) {
		Specification<MessageDiscuss> specification = createSpecification(whereMap);
		return messageDiscussDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public MessageDiscuss findById(String id) {
		return messageDiscussDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param messageDiscuss
	 */
	public void add(MessageDiscuss messageDiscuss) {
		messageDiscuss.setMessagediscussid(IdUtil.simpleUUID()+"");
		messageDiscussDao.save(messageDiscuss);
	}

	/**
	 * 修改
	 * @param messageDiscuss
	 */
	public void update(MessageDiscuss messageDiscuss) {
		messageDiscussDao.save(messageDiscuss);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		messageDiscussDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<MessageDiscuss> createSpecification(Map searchMap) {

		return new Specification<MessageDiscuss>() {

			@Override
			public Predicate toPredicate(Root<MessageDiscuss> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				// 评论id
				if (searchMap.get("messagediscussid")!=null && !"".equals(searchMap.get("messagediscussid"))) {
					predicateList.add(cb.like(root.get("messagediscussid").as(String.class), "%"+(String)searchMap.get("messagediscussid")+"%"));
				}
				// 评论父id
				if (searchMap.get("messagediscussfatherid")!=null && !"".equals(searchMap.get("messagediscussfatherid"))) {
					predicateList.add(cb.like(root.get("messagediscussfatherid").as(String.class), "%"+(String)searchMap.get("messagediscussfatherid")+"%"));
				}
				// 留言id
				if (searchMap.get("messageid")!=null && !"".equals(searchMap.get("messageid"))) {
					predicateList.add(cb.like(root.get("messageid").as(String.class), "%"+(String)searchMap.get("messageid")+"%"));
				}
				// 评论人
				if (searchMap.get("useraid")!=null && !"".equals(searchMap.get("useraid"))) {
					predicateList.add(cb.like(root.get("useraid").as(String.class), "%"+(String)searchMap.get("useraid")+"%"));
				}
				// 评论内容
				if (searchMap.get("discusscontent")!=null && !"".equals(searchMap.get("discusscontent"))) {
					predicateList.add(cb.like(root.get("discusscontent").as(String.class), "%"+(String)searchMap.get("discusscontent")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
