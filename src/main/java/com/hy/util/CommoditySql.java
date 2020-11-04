package com.hy.util;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import org.apache.ibatis.annotations.Param;

public class CommoditySql {

    public String CommoditySql(@Param("createTime")String createTime,@Param("createTimes")String createTimes){
        StringBuffer sql=new StringBuffer("select c.sid, c.`name`,i.cas,u.user_name ,price_info,c.create_time,c.update_time,c.supplier_id \n" +
                "from commodity c, supplier s , users u,inventory i " +
                "where c.user_id=u.uid and c.cas = i.cas and c.supplier_id=s.gid  ");
            if(createTime !=null && createTimes != null){
                sql.append(" and c.create_time between '"+createTime+"' and '"+createTimes+" ' ");
            }

            sql.append("order by i.amount desc ");
            System.out.println(sql);
        return sql.toString();
    }

}
