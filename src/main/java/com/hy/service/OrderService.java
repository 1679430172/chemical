package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Authority;
import com.hy.bean.Order;
import com.hy.mapper.OrderMapper;
import com.hy.util.ParseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private OrderMapper orderMapper;

    public ParseData selectList(Page page){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        IPage<Order> iPage=null;
        if(session.getAttribute("userType").equals(Authority.administrator)){
            iPage=orderMapper.selectList(page);
        }else if(session.getAttribute("userType").equals(Authority.authorizedSalesman)){
            iPage=orderMapper.selectList(page);
        }else if(session.getAttribute("userType").equals(Authority.salesman)){
            iPage=orderMapper.selectListByUserId((Integer)session.getAttribute("userId"),page);
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    public Integer add(Order order){
        return orderMapper.insert(order);
    }

    public ParseData selectListByStatus(Page page){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        IPage<Order> iPage=null;
        if(session.getAttribute("userType").equals(Authority.administrator)){
            iPage=orderMapper.selectListByStatus(page);
        }else if(session.getAttribute("userType").equals(Authority.authorizedSalesman)){
            iPage=orderMapper.selectListByStatus(page);
        }else if(session.getAttribute("userType").equals(Authority.salesman)){
            iPage=orderMapper.selectListByStatus((Integer)session.getAttribute("userId"),page);
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }
}
