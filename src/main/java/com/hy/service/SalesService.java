package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Sales;
import com.hy.mapper.SalesMapper;
import com.hy.util.ParseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SalesService extends ServiceImpl<SalesMapper, Sales> {
    @Autowired
    private SalesMapper salesMapper;

    /**
     * 验证业务员权限查询退货订单
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public ParseData getSalesbyuserId(String userId,Integer page,Integer limit) {
        Page<Sales> page1 = new Page<Sales>(page,limit);
        IPage<Sales> iPage=salesMapper.selectSales(page1,Integer.parseInt(userId));
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

}
