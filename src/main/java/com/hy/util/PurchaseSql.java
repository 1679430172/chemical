package com.hy.util;

import com.hy.bean.Inventory;
import com.hy.bean.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseSql {
    @Autowired
    private Purchase purchase;


    public String query(Purchase purchase){
        StringBuffer sql = null;
        if(purchase!=null&&!purchase.equals("")){

            sql = new StringBuffer("select * from purchase where 1=1");
            if(purchase.getName() != null&&!"".equals(purchase.getName())){
                sql.append(" and name like '%"+purchase.getName()+"%'");
            }
            if (purchase.getCas() != null&&!"".equals(purchase.getCas())){
                sql.append(" and cas='"+purchase.getCas()+"'");
            }
            if (purchase.getSupplierName() != null&&!"".equals(purchase.getSupplierName())){
                sql.append(" and supplier_name="+purchase.getSupplierName());
            }
        }
        return sql.toString();
    }

}
