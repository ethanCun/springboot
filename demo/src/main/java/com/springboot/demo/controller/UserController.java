package com.springboot.demo.controller;

import com.springboot.demo.base.controller.BaseController;
import com.springboot.demo.base.utils.DateUtils;
import com.springboot.demo.base.utils.StateParameter;
import com.springboot.demo.base.utils.UUIDUtils;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.UserService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap add(User user){

        try{

            Date createDate = DateUtils.parseDate(user.getCreateDate(), "yyyy-MM-dd");
            Date updateDate = DateUtils.parseDate(user.getUpdateDate(), "yyyy-MM-dd");

            System.out.println(user.getCreateDate());
            System.out.println(user.getUpdateDate());
            System.out.println("createTime = " + createDate);
            System.out.println("updateTime = " + updateDate);

            userService.save(user);

            logger.info("保存成功");

            return getModelMap(StateParameter.SUCCESS, user, "保存成功");

        }catch (Exception e){

            e.printStackTrace();

            return getModelMap(StateParameter.FAULT, null, "保存失败");
        }
    }


    @RequestMapping(value = "/findById")
    @ResponseBody
    public ModelMap findById(int id){

        try {

            User user = userService.findById(id);

            if (user == null){

                return getModelMap(StateParameter.FAULT, null, "不存在");
            }

            return getModelMap(StateParameter.SUCCESS, user, "查找成功");

        }catch (Exception e){

            e.printStackTrace();

            return getModelMap(StateParameter.FAULT, null, "查找失败");
        }
    }

    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteUserById(int id){

        try {

            userService.deleteUserById(id);

            return getModelMap(StateParameter.SUCCESS, null, "删除成功");

        }catch (Exception e){

            return getModelMap(StateParameter.FAULT, null, "删除失败");
        }
    }

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public ModelMap findAll(){

        try{

            List<User> users = userService.findAll();

            return getModelMap(StateParameter.SUCCESS, users, "查找成功");

        }catch (Exception e){

            return getModelMap(StateParameter.FAULT, null, "查找失败");
        }
    }

    @RequestMapping(value = "/list")
    public String userList(HttpServletRequest request){

        List<User> users = userService.findAll();

        request.setAttribute("users", users);

        return "/demoPage/firstPage";
    }
}
