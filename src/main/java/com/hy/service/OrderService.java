package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Authority;
import com.hy.bean.Order;
import com.hy.bean.SupplierUsers;
import com.hy.mapper.OrderMapper;
import com.hy.util.ParseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private InventoService inventoService;

    @Autowired
    private CommodityService commodityService;

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
        List<Order> list=iPage.getRecords();
        for(Order s:list){
            String did=""+s.getDid();
            String id="YD00000";
            id=id.substring(0,id.length()-did.length())+did;
            s.setId(id);
            s.setType((String) session.getAttribute("userType"));
            s.setUid((Integer) session.getAttribute("userId"));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    public Integer add(Order order){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        order.setUserId((Integer) session.getAttribute("userId"));
        order.setStatus("0");
        order.setCreateTime(new Date());
        if(null==order.getBill()){
            order.setBill("0");
        }
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
            iPage=orderMapper.selectListByStatust((Integer)session.getAttribute("userId"),page);
        }
        List<Order> list=iPage.getRecords();
        for(Order s:list){
            String did=""+s.getDid();
            String id="YD00000";
            id=id.substring(0,id.length()-did.length())+did;
            s.setId(id);
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    public Integer updStatus(Integer did){
        Order order=orderMapper.selectById(did);
        inventoService.aUpdate(order.getAmount(),order.getInvoiceId());
        return orderMapper.updateStatus(did);
    }

    public ParseData selectListTime(String stadate,String enddate,Page page){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        IPage<Order> iPage=null;
        if(session.getAttribute("userType").equals(Authority.administrator)){
            iPage=orderMapper.selectListTime(stadate,enddate,page);
        }else if(session.getAttribute("userType").equals(Authority.authorizedSalesman)){
            iPage=orderMapper.selectListTime(stadate,enddate,page);
        }else if(session.getAttribute("userType").equals(Authority.salesman)){
            iPage=orderMapper.selectListByUserIdTime(stadate,enddate,(Integer)session.getAttribute("userId"),page);
        }
        List<Order> list=iPage.getRecords();
        for(Order s:list){
            String did=""+s.getDid();
            String id="YD00000";
            id=id.substring(0,id.length()-did.length())+did;
            s.setId(id);
            s.setType((String) session.getAttribute("userType"));
            s.setUid((Integer) session.getAttribute("userId"));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

}
