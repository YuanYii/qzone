package top.yuanyii.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import top.yuanyii.dao.ImageDiscussDao;
import top.yuanyii.pojo.photo.ImageDiscuss;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ImageDiscussService {

	@Autowired
	private ImageDiscussDao imageDiscussDao;
	

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<ImageDiscuss> findAll() {
		return imageDiscussDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ImageDiscuss> findSearch(Map whereMap, int page, int size) {
		Specification<ImageDiscuss> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return imageDiscussDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<ImageDiscuss> findSearch(Map whereMap) {
		Specification<ImageDiscuss> specification = createSpecification(whereMap);
		return imageDiscussDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ImageDiscuss findById(String id) {
		return imageDiscussDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param imageDiscuss
	 */
	public void add(ImageDiscuss imageDiscuss) {
		imageDiscuss.setImagediscussid( IdUtil.simpleUUID() +"" );
		imageDiscussDao.save(imageDiscuss);
	}

	/**
	 * 修改
	 * @param imageDiscuss
	 */
	public void update(ImageDiscuss imageDiscuss) {
		imageDiscussDao.save(imageDiscuss);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		imageDiscussDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<ImageDiscuss> createSpecification(Map searchMap) {

		return new Specification<ImageDiscuss>() {

			@Override
			public Predicate toPredicate(Root<ImageDiscuss> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 照片评论id
                if (searchMap.get("imagediscussid")!=null && !"".equals(searchMap.get("imagediscussid"))) {
                	predicateList.add(cb.like(root.get("imagediscussid").as(String.class), "%"+(String)searchMap.get("imagediscussid")+"%"));
                }
                // 评论父id
                if (searchMap.get("discussfatherid")!=null && !"".equals(searchMap.get("discussfatherid"))) {
                	predicateList.add(cb.like(root.get("discussfatherid").as(String.class), "%"+(String)searchMap.get("discussfatherid")+"%"));
                }
                // 照片id
                if (searchMap.get("imgid")!=null && !"".equals(searchMap.get("imgid"))) {
                	predicateList.add(cb.like(root.get("imgid").as(String.class), "%"+(String)searchMap.get("imgid")+"%"));
                }
                // 评论人
                if (searchMap.get("discussuser")!=null && !"".equals(searchMap.get("discussuser"))) {
                	predicateList.add(cb.like(root.get("discussuser").as(String.class), "%"+(String)searchMap.get("discussuser")+"%"));
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
