package top.yuanyii.service;

import java.util.*;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import top.yuanyii.dao.UserDao;
import top.yuanyii.pojo.user.User;
import top.yuanyii.sms.SendSms;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userDao.findAll(specification, pageRequest);
	}

	/**
	 * 发送短信
	 * @param phone
	 * @return
	 */
	public Map<String,Integer> getCode(String phone){
		Integer code = RandomUtil.randomInt(6);
		SendSms.send(code,phone);
		Map<String,Integer> codeMap = new HashMap<>();
		codeMap.put(phone,code);
		return codeMap;
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		user.setUid(IdUtil.simpleUUID()+"" );
		userDao.save(user);
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 用户id
                if (searchMap.get("uid")!=null && !"".equals(searchMap.get("uid"))) {
                	predicateList.add(cb.like(root.get("uid").as(String.class), "%"+(String)searchMap.get("uid")+"%"));
                }
                // 用户名
                if (searchMap.get("uname")!=null && !"".equals(searchMap.get("uname"))) {
                	predicateList.add(cb.like(root.get("uname").as(String.class), "%"+(String)searchMap.get("uname")+"%"));
                }
                // 用户密码
                if (searchMap.get("upass")!=null && !"".equals(searchMap.get("upass"))) {
                	predicateList.add(cb.like(root.get("upass").as(String.class), "%"+(String)searchMap.get("upass")+"%"));
                }
                // 密码盐值
                if (searchMap.get("usalt")!=null && !"".equals(searchMap.get("usalt"))) {
                	predicateList.add(cb.like(root.get("usalt").as(String.class), "%"+(String)searchMap.get("usalt")+"%"));
                }
                // 用户邮箱
                if (searchMap.get("uemail")!=null && !"".equals(searchMap.get("uemail"))) {
                	predicateList.add(cb.like(root.get("uemail").as(String.class), "%"+(String)searchMap.get("uemail")+"%"));
                }
                // 真实姓名
                if (searchMap.get("urealname")!=null && !"".equals(searchMap.get("urealname"))) {
                	predicateList.add(cb.like(root.get("urealname").as(String.class), "%"+(String)searchMap.get("urealname")+"%"));
                }
                // 用户手机号
                if (searchMap.get("uphone")!=null && !"".equals(searchMap.get("uphone"))) {
                	predicateList.add(cb.like(root.get("uphone").as(String.class), "%"+(String)searchMap.get("uphone")+"%"));
                }
                // 头像id
                if (searchMap.get("uimg")!=null && !"".equals(searchMap.get("uimg"))) {
                	predicateList.add(cb.like(root.get("uimg").as(String.class), "%"+(String)searchMap.get("uimg")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
