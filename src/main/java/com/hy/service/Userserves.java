package com.hy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.User;
import com.hy.mapper.UserMapper;
import com.hy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class Userserves extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆页面查询名字
     * @param name
     * @return
     */
    public String selecet(String name){
        User user=userMapper.select(name);
        if(user!=null){
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("id",user.getType());
            return Util.sueess;
        }else{
            return Util.defact;
        }
    };
}
