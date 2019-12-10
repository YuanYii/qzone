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

import top.yuanyii.dao.ImageDao;
import top.yuanyii.pojo.photo.Image;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ImageService {

	@Autowired
	private ImageDao imageDao;
	
	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Image> findAll() {
		return imageDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Image> findSearch(Map whereMap, int page, int size) {
		Specification<Image> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return imageDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Image> findSearch(Map whereMap) {
		Specification<Image> specification = createSpecification(whereMap);
		return imageDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Image findById(String id) {
		return imageDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param image
	 */
	public void add(Image image) {
		image.setImgid( IdUtil.simpleUUID() +"" );
		imageDao.save(image);
	}

	/**
	 * 修改
	 * @param image
	 */
	public void update(Image image) {
		imageDao.save(image);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		imageDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Image> createSpecification(Map searchMap) {

		return new Specification<Image>() {

			@Override
			public Predicate toPredicate(Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // imgID
                if (searchMap.get("imgid")!=null && !"".equals(searchMap.get("imgid"))) {
                	predicateList.add(cb.like(root.get("imgid").as(String.class), "%"+(String)searchMap.get("imgid")+"%"));
                }
                // 图片地址
                if (searchMap.get("imgsrc")!=null && !"".equals(searchMap.get("imgsrc"))) {
                	predicateList.add(cb.like(root.get("imgsrc").as(String.class), "%"+(String)searchMap.get("imgsrc")+"%"));
                }
                // 作者
                if (searchMap.get("imguseraid")!=null && !"".equals(searchMap.get("imguseraid"))) {
                	predicateList.add(cb.like(root.get("imguseraid").as(String.class), "%"+(String)searchMap.get("imguseraid")+"%"));
                }
                // 照片描述
                if (searchMap.get("imgdesc")!=null && !"".equals(searchMap.get("imgdesc"))) {
                	predicateList.add(cb.like(root.get("imgdesc").as(String.class), "%"+(String)searchMap.get("imgdesc")+"%"));
                }
                // 所属相册
                if (searchMap.get("albumid")!=null && !"".equals(searchMap.get("albumid"))) {
                	predicateList.add(cb.like(root.get("albumid").as(String.class), "%"+(String)searchMap.get("albumid")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
