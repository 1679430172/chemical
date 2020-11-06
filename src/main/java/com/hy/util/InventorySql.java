package com.hy.util;

import com.hy.bean.Inventory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public class InventorySql {
    @Autowired
    private Inventory inventory;

    public String query(Inventory inventory){
        StringBuffer sql = null;
        if(inventory!=null&&!inventory.equals("")){

            sql = new StringBuffer("select * from Inventory where 1=1");
            if(inventory.getName() != null&&!"".equals(inventory.getName())){
                sql.append(" and name like '%"+inventory.getName()+"%'");
            }
            if (inventory.getCas() != null&&!"".equals(inventory.getCas())){
                sql.append(" and cas='"+inventory.getCas()+"'");
            }
            if (inventory.getNumber() != null&&!"".equals(inventory.getNumber())){
                sql.append(" and number="+inventory.getNumber());
            }
        }
        return sql.toString();
    }


}
