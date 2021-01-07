package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Order;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
import com.hy.mapper.OrderMapper;
import com.hy.mapper.SupplierMapper;
import com.hy.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier> {
    @Autowired
    private SupplierMapper supplierMapper;

    public IPage<SupplierUsers> iPage(Integer page, Integer limit,SupplierUsers name){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        Integer userId= (Integer) session.getAttribute("userId");
        IPage<SupplierUsers> iPage=null;
        if(userType.equals("0") || userType.equals("2")){
             iPage= supplierMapper.suppliers(new Page(page,limit),name);
            List<SupplierUsers> list=iPage.getRecords();
            for(SupplierUsers s:list){
                String gid=""+s.getGid();
                String id="AH00000";
                id=id.substring(0,id.length()-gid.length())+gid;
                s.setId(id);
            }
            return iPage;
        }else { 
            name.setUid(userId);
            iPage=supplierMapper.suppliers(new Page(page,limit),name);
            List<SupplierUsers> list=iPage.getRecords();
            for(SupplierUsers s:list){
                String gid=""+s.getGid();
                String id="AH00000";
                id=id.substring(0,id.length()-gid.length())+gid;
                s.setId(id);
            }
            return iPage;
        }
    }

    public List<SupplierUsers> iPage(){
        List<SupplierUsers> iPage= supplierMapper.supplier();
        return iPage;
    }

    public Supplier suppliers(String gid){
        return supplierMapper.selectById(gid);
    }

    public List<SupplierUsers> users(){
        return supplierMapper.users();
    }

    public Integer update(Supplier supplier) {
        return supplierMapper.update(supplier);
    }

    public Integer delete(String gid){
        return supplierMapper.delete(gid);
    }

    public List<SupplierUsers> supplierlist(Supplier name){
        return supplierMapper.supplierlist(name);
    }
    public void updateById(Integer id){
        supplierMapper.updateById(id);
    }
}
