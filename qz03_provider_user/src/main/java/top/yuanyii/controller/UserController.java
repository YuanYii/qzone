package top.yuanyii.controller;
import	java.util.HashMap;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.yuanyii.entity.PageResult;
import top.yuanyii.entity.Result;
import top.yuanyii.entity.StatusCode;
import top.yuanyii.pojo.user.User;
import top.yuanyii.service.UserService;
import top.yuanyii.sms.SendSms;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    public static Map<String, Integer> newCode = new HashMap<> ();


    @Autowired
    private UserService userService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param   user
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody User user) {
        Map<String, String> searchMap = new HashMap<String, String> ();
        String uphone = user.getUphone();
        String upass = user.getUpass();
        searchMap.put("uphone", uphone);
        searchMap.put("upass",upass);
        List<User> search = userService.findSearch(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user,@RequestParam Integer code) {

        if (code == null){
            return new Result(false, StatusCode.ERROR, "请输入短信验证码");
        }else if (!code.equals(newCode.get(user.getUphone())) ){
            return new Result(false, StatusCode.ERROR, "验证码不正确");
        }else {
            userService.add(user);
            return new Result(true, StatusCode.OK, "增加成功");
        }
    }


    @RequestMapping(value = "code" , method = RequestMethod.POST)
    public Result code(@RequestBody User user){
        newCode = userService.getCode(user.getUphone());
        return new Result(true,StatusCode.OK,"发送短信成功");
    }


    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setUid(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
