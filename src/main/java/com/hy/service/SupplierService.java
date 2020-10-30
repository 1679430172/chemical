package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Order;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.mapper.OrderMapper;
import com.hy.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier> {
    @Autowired
    private SupplierMapper supplierMapper;

    public IPage<SupplierUsers> iPage(Integer page, Integer limit){
        IPage<SupplierUsers> iPage= supplierMapper.supplier(new Page(page,limit));
        System.out.println(iPage.getRecords().get(0).getCreateTime()+"------------");
        return iPage;
    }
}
