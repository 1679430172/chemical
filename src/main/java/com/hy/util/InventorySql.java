package com.hy.util;

import com.hy.bean.Inventory;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;

public class InventorySql {
    @Autowired
    private Inventory inventory;

    public String query(@Param("em") Inventory inventory){
        StringBuffer sql = null;
        if(inventory!=null&&!inventory.equals("")){
            System.out.println(inventory.toString());
            sql = new StringBuffer("select * from Inventory where 1=1");
            if(inventory.getName() != null&&!"".equals(inventory.getName())){
                sql.append(" and name like '%"+inventory.getName()+"%'");
            }
            if (inventory.getCas() != null&&!"".equals(inventory.getCas())){
                sql.append(" and cas='"+inventory.getCas()+"'");
            }
            if (inventory.getNumber1() != null&&!"".equals(inventory.getNumber1())){
                sql.append(" and number like'%"+inventory.getNumber1()+"%'");
            }
            System.out.println(sql);
        }
        return sql.toString();
    }


}
