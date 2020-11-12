package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Inventory;
import com.hy.mapper.InventoryMapper;
import com.hy.util.Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public  String add(Inventory inventory)throws Exception{
        Inventory b=inventoryMapper.select(inventory.getNumber(),inventory.getCas());
        if(b!=null){
            Inventory inventory1=inventoryMapper.selecttwo(inventory.getNumber(),inventory.getCas());
            inventoryMapper.updateinventory(inventory.getAmount()+inventory1.getAmount(),inventory.getNumber());
            return Util.sueess;
        }else{
            Inventory b1=inventoryMapper.selectnumber(inventory.getNumber());
            Inventory b2=inventoryMapper.selectcas(inventory.getCas());
            if(b1==null &&b2==null){
                inventoryMapper.insertinventory(inventory);
                return  Util.sueess;
            }else {
                return Util.defact;
            }
        }

    }


}
