package com.hy.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.*;
import com.hy.mapper.PurchaseMapper;
import com.hy.mapper.UserMapper;
import com.hy.util.ParseData;
import com.hy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class PurchaseService extends ServiceImpl<PurchaseMapper, Purchase> {
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private UserMapper userMapper;

    public IPage<Purchase> iPage(Integer page, Integer limit,Purchase purchase){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        IPage<Purchase> iPage=purchaseMapper.queryBy(new Page(page,limit),purchase);
        List<Purchase> list=iPage.getRecords();
        for(Purchase s:list){
            Integer userif=s.getUserId();
            User user=userMapper.selectbyid(userif);
            s.setUserName(user.getUsername());
            String cid=""+s.getCid();
            String id="CG00000";
            id=id.substring(0,id.length()-cid.length())+cid;
            s.setGid(id);
            s.setSessionid(userType);
            s.setUserId((Integer) session.getAttribute("userId"));
        }
        return iPage;
    }

    public ParseData selectList(Page page){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        IPage<Purchase> iPage=null;
        if(session.getAttribute("userType").equals(Authority.administrator)){
            iPage=purchaseMapper.Purchase(page);
        }else if(session.getAttribute("userType").equals(Authority.authorizedSalesman)){
            iPage=purchaseMapper.Purchase(page);
        }else if(session.getAttribute("userType").equals(Authority.salesman)){
            iPage=purchaseMapper.supplier((Integer)session.getAttribute("userId"),page);
        }
        List<Purchase> list1=iPage.getRecords();
        for(Purchase s:list1){ ;
            s.setGid(String.format("CG%05d",s.getCid()));
            s.setSessionid(userType);
//            s.setUserId((Integer) session.getAttribute("userId"));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    public static void main(String[] args) {
        System.out.println(String.format("CG%05d",2));
    }


    public void updateAnn(String cid){
       purchaseMapper.updateAnn(cid);
    }

    public void updateTN(String cid){purchaseMapper.updateTN(cid);}


    public void  equals(Purchase purchase){
        purchaseMapper.equals(purchase);
    }

    public boolean save(Purchase purchase){
        System.out.println("~~~~"+purchase.getName());
        if(purchase.getPriceStatus().equals("0")){
            purchaseMapper.addPurchase(purchase);
            return true;
        }
        purchaseMapper.adPurchase(purchase);
        return true;
    }
    public IPage<Purchase> wPage(Integer page, Integer limit){
        IPage<Purchase> iPage= purchaseMapper.Purchase(new Page(page,limit));
        return iPage;
    }


    public Integer wwww(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Integer userId= (Integer) session.getAttribute("userId");
        return userId;
    }

    public String detelep(String cid){
        try {
            purchaseMapper.detelep(cid);
        } catch (Exception e) {
            return Util.defact;
        }
        return Util.sueess;
    }

}
