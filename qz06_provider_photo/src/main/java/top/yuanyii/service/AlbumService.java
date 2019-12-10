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

import top.yuanyii.dao.AlbumDao;
import top.yuanyii.pojo.photo.Album;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class AlbumService {

	@Autowired
	private AlbumDao albumDao;
	
	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Album> findAll() {
		return albumDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Album> findSearch(Map whereMap, int page, int size) {
		Specification<Album> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return albumDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Album> findSearch(Map whereMap) {
		Specification<Album> specification = createSpecification(whereMap);
		return albumDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Album findById(String id) {
		return albumDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param album
	 */
	public void add(Album album) {
		album.setAlbumid( IdUtil.simpleUUID() +"" );
		albumDao.save(album);
	}

	/**
	 * 修改
	 * @param album
	 */
	public void update(Album album) {
		albumDao.save(album);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		albumDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Album> createSpecification(Map searchMap) {

		return new Specification<Album>() {

			@Override
			public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get("albumid")!=null && !"".equals(searchMap.get("albumid"))) {
                	predicateList.add(cb.like(root.get("albumid").as(String.class), "%"+(String)searchMap.get("albumid")+"%"));
                }
                // 相册名
                if (searchMap.get("albumname")!=null && !"".equals(searchMap.get("albumname"))) {
                	predicateList.add(cb.like(root.get("albumname").as(String.class), "%"+(String)searchMap.get("albumname")+"%"));
                }
                // 相册描述
                if (searchMap.get("albumdesc")!=null && !"".equals(searchMap.get("albumdesc"))) {
                	predicateList.add(cb.like(root.get("albumdesc").as(String.class), "%"+(String)searchMap.get("albumdesc")+"%"));
                }
                // 相册地址
                if (searchMap.get("albumaddress")!=null && !"".equals(searchMap.get("albumaddress"))) {
                	predicateList.add(cb.like(root.get("albumaddress").as(String.class), "%"+(String)searchMap.get("albumaddress")+"%"));
                }
                // 相册分类
                if (searchMap.get("albumclassify")!=null && !"".equals(searchMap.get("albumclassify"))) {
                	predicateList.add(cb.like(root.get("albumclassify").as(String.class), "%"+(String)searchMap.get("albumclassify")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
