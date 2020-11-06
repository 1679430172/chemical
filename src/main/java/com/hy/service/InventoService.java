package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Inventory;
import com.hy.mapper.InventoryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoService extends ServiceImpl<InventoryMapper, Inventory> {
    @Autowired
    private InventoryMapper inventoryMapper;

    public IPage<Inventory> iPage(Integer page, Integer limit){
        IPage<Inventory> iPage= inventoryMapper.Inventory(new Page(page,limit));
        return iPage;
    }

    public IPage<Inventory> querylist(Integer page, Integer limit, Inventory inventory){
        return (IPage<Inventory>) inventoryMapper.queryBy(new Page<Inventory>(page,limit),inventory);
    }


}
