package com.hy.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Login;
import com.hy.bean.User;
import com.hy.service.Userserves;
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


@Controller
@RequestMapping("/login")
public class Logincro {

    @Autowired
    private Userserves userserves;


    @RequestMapping("login.do")
    @ResponseBody
    public String  Login(@RequestBody User user){
        return userserves.selecet(user.getUsername());
    }
}
