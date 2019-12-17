package top.yuanyii.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yuanyii.entity.PageResult;
import top.yuanyii.entity.Result;
import top.yuanyii.entity.StatusCode;
import top.yuanyii.pojo.talk.Talk;
import top.yuanyii.service.TalkService;

/**
 * 控制器层
 * @author Administrator
 *
 */
@Controller
//@CrossOrigin
@RequestMapping("/talk")
public class TalkController {

	@Autowired
	private TalkService talkService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping("/all")
    @ResponseBody
	public Result findAll(){

		return new Result(true, StatusCode.OK,"查询成功",talkService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",talkService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Talk> pageList = talkService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Talk>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",talkService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param talk
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Talk talk  ){
		talkService.add(talk);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param talk
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Talk talk, @PathVariable String id ){
		talk.setTalkid(id);
		talkService.update(talk);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		talkService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
