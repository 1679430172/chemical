package com.hy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Invoice;
import com.hy.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService extends ServiceImpl<InvoiceMapper,Invoice>{
    @Autowired
    private InvoiceMapper invoiceMapper;

    public IPage<Invoice> queryByCas(String cas, String name, Page page){
        return invoiceMapper.queryBycass(name,cas,page);
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
