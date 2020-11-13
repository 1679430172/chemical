package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Inventory;
import com.hy.bean.Purchase;
import com.hy.bean.SupplierUsers;
import com.hy.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService extends ServiceImpl<PurchaseMapper, Purchase> {
    @Autowired
    private PurchaseMapper purchaseMapper;

    public IPage<Purchase> iPage(Integer page, Integer limit){

        IPage<Purchase> iPage=purchaseMapper.Purchase(new Page(page,limit));

        return iPage;
    }

    public void updateAnn(String cid){
        purchaseMapper.updateAnn(cid);
    }

    public void updateTN(String cid){purchaseMapper.updateTN(cid);}

    public boolean save(Purchase purchase){
        purchaseMapper.addPurchase(purchase);
        return true;
    }
    public IPage<Purchase> wPage(Integer page, Integer limit){
        IPage<Purchase> iPage= purchaseMapper.Purchase(new Page(page,limit));
        return iPage;
    }
}
