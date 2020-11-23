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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

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
    public User getbyid(@Param("id") String id){
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
        return  userserves.update(user);
    }

    /**
     * 查询权限类型
     * @return
     */
    @RequestMapping("/selecttype")
    @ResponseBody
    public List<User> selecttype(){
        return  userserves.selecttype();
    }

    /**
     * 插入业务员信息
     * @param user
     * @return
     */
    @RequestMapping("/insertpp")
    @ResponseBody
    public String ttt(User user){
        return userserves.tt(user);
    }

    /**
     * 删除业务员信息服务端
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String detele(Integer id){
        return userserves.detele(id);
    }

    /**
     * 获取sesiion中的业务员id
     * @return
     */
    @RequestMapping("/getSessionUserId")
    @ResponseBody
    public String getSessionUserId(){
        return userserves.getSessionUserId();
    }


    /**
     * 注销登陆
     * @return
     */
    @RequestMapping("/zhuxiao")
    @ResponseBody
    public String zhuxiao(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return Util.sueess;
    }

}
