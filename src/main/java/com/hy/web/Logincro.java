package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Login;
import com.hy.bean.User;
import com.hy.service.Userserves;
import com.hy.util.ParseData;
import com.hy.util.Util;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/login")
public class Logincro {

    @Autowired
    private Userserves userserves;

    /**
     * 登陆页面服务端
     * @param user
     * @return
     */
    @RequestMapping("login.do")
    @ResponseBody
    public String  Login(@RequestBody User user){
        return userserves.selecet(user);
    }

    /**
     * 业务员服务端
     * @return
     */
    @RequestMapping("/yewuyuan")
    @ResponseBody
    public ParseData Yewuyaun(Integer page ,Integer limit){
        return userserves.selectlist(page, limit);
    }

    /**
     * 获取session（权限管理）
     * @param request
     * @return
     */
    @RequestMapping("/session")
    @ResponseBody
    public String getsession(HttpServletRequest request){
        return userserves.getsession(request);
    }

    /**
     * 通过id查询当前业务员服務端
     * @param id
     * @return
     */
    @RequestMapping("/getbyid")
    @ResponseBody
    public User getbyid(String id){
        return userserves.getbyid(id);
    }

    /**
     * 修改业务员信息服务端
     * @param user
     * @return
     */
    @RequestMapping("/updated")
    @ResponseBody
    public String update( User user){
        System.out.println("user======="+user);
        return  userserves.update(user);
    }


}
