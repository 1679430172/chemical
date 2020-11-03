package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.mapper.CommodityMapper;
import com.hy.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
    @Autowired
    private CommodityMapper commodityMapper;

    public IPage<Commoditys> CommditysList(Integer page, Integer limit,Commoditys commoditys){
        return commodityMapper.CommditysList(new Page(page,limit),commoditys);
    }

    public List<Commodity> pictures(InputStream inputStream){
        return null;
    }

}
