package com.hy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Inventory;
import com.hy.bean.Invoice;
import com.hy.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

@Service
public class InvoiceService extends ServiceImpl<InvoiceMapper,Invoice>{
    @Autowired
    private InvoiceMapper invoiceMapper;

    public List<Invoice> queryByCas(String cas,String name){
        QueryWrapper queryWrapper=new QueryWrapper();
        if(null!=cas && cas!=""){
            queryWrapper.eq("cas",cas);
        }
        if(null!=name && name!=""){
            queryWrapper.like("name",name);

        }
        return invoiceMapper.selectList(queryWrapper);
    }

    public Integer updateBySid(Invoice invoice){
        boolean bl=invoiceMapper.update(invoice.getNumber(),invoice.getSid());
        if(!bl){
            return 0;
        }
        return 1;
    }

    public Integer autoUpdateBySid(Invoice invoice){
        boolean bl=invoiceMapper.autoUpdate(invoice.getNumber(),invoice.getSid());
        if(!bl){
            return 0;
        }
        return 1;
    }

    public Invoice queryBysid(Integer sid){
        return invoiceMapper.queryBysid(sid);
    }

    public Integer updatesid(Invoice invoice){
        boolean bl=invoiceMapper.updatesid(invoice);
        if(bl){
            return 1;
        }
        return 0;
    }

}
