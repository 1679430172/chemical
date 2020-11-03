package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.User;
import com.hy.mapper.UserMapper;
import com.hy.util.ParseData;
import com.hy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class Userserves extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆页面查询名字(id是权限类型，uid是编号)
     * @param user1
     * @return
     */
    public String selecet(User user1){
        User user=userMapper.select(user1);
        if(user!=null){
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("id",user.getType());
            session.setAttribute("uid",user.getId());
            session.setAttribute("username",user.getUsername());
            return Util.sueess;
        }else{
            return Util.defact;
        }
    };

    /**
     * 所有业务员信息
     * @return
     */
    public ParseData selectlist(Integer page ,Integer limit){
        ParseData parseData = new ParseData();
        parseData.setCode(0);
        parseData.setMsg("");
        Page<User> page1 = new Page<User>(page,limit);
        parseData.setCount(Integer.parseInt(String.valueOf(userMapper.selectlist(page1).getSize())));
        parseData.setData(userMapper.selectlist(page1).getRecords());
        return parseData;
    }

    /**
     * 权限管理获取session(id是权限类型，uid是编号)
     * @param request
     * @return
     */
    public String getsession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String uid = (String) session.getAttribute("uid");
        String username = (String) session.getAttribute("username");
        return id;
    }

    /**
     * 通过id查询当前业务员
     * @param id
     * @return
     */
    public User getbyid(String id){
      return userMapper.selectbyid(Integer.parseInt(id));
    }

    /**
     * 通過id修改业务员
     * @param user
     * @return
     */
    public String update(User user){
        try {
            userMapper.update(user);
        } catch (Exception e) {
            return Util.defact;
        }
        return Util.sueess;
    }

    /**
     * 查询权限类型
     * @return
     */
    public List<User> selecttype(){
        return userMapper.selcttype();
    }

    /**
     * 插入业务员信息
     * @param user
     * @return
     */
    public String tt(User user){
        try {
            System.out.println(user);
            userMapper.ttt(user);
        } catch (Exception e) {
            return Util.defact;
        }
        return Util.sueess;
    }

}
