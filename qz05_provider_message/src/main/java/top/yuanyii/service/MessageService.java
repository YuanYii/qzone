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

import top.yuanyii.dao.MessageDao;
import top.yuanyii.pojo.message.Message;



/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Message> findAll() {
		return messageDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Message> findSearch(Map whereMap, int page, int size) {
		Specification<Message> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return messageDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Message> findSearch(Map whereMap) {
		Specification<Message> specification = createSpecification(whereMap);
		return messageDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Message findById(String id) {
		return messageDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param message
	 */
	public void add(Message message) {
		message.setMessageid(IdUtil.simpleUUID()+"");
		messageDao.save(message);
	}

	/**
	 * 修改
	 * @param message
	 */
	public void update(Message message) {
		messageDao.save(message);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		messageDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Message> createSpecification(Map searchMap) {

		return new Specification<Message>() {

			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 留言id
                if (searchMap.get("messageid")!=null && !"".equals(searchMap.get("messageid"))) {
                	predicateList.add(cb.like(root.get("messageid").as(String.class), "%"+(String)searchMap.get("messageid")+"%"));
                }
                // 留言作者
                if (searchMap.get("messageuseraid")!=null && !"".equals(searchMap.get("messageuseraid"))) {
                	predicateList.add(cb.like(root.get("messageuseraid").as(String.class), "%"+(String)searchMap.get("messageuseraid")+"%"));
                }
                // 被留言的人
                if (searchMap.get("messageuserbid")!=null && !"".equals(searchMap.get("messageuserbid"))) {
                	predicateList.add(cb.like(root.get("messageuserbid").as(String.class), "%"+(String)searchMap.get("messageuserbid")+"%"));
                }
                // 留言内容
                if (searchMap.get("messagecontent")!=null && !"".equals(searchMap.get("messagecontent"))) {
                	predicateList.add(cb.like(root.get("messagecontent").as(String.class), "%"+(String)searchMap.get("messagecontent")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
