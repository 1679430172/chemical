package com.hy.util;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import org.apache.ibatis.annotations.Param;

public class CommoditySql {

    public String CommoditySql(@Param("commoditys") Commoditys commoditys){
        StringBuffer sql=new StringBuffer("select c.sid, c.`name`,i.cas,u.user_name ,price_info,c.create_time,c.update_time\n" +
                "from commodity c, supplier s , users u,inventory i " +
                "where c.user_id=u.uid and c.cas = i.cas and c.supplier_id=s.gid  order by i.amount desc ");
        if(commoditys != null && !commoditys.equals("")){
            if( !"".equals(commoditys.getUpdateTime()) && commoditys.getUpdateTime() != null && !"".equals(commoditys.getCreateTimes()) && commoditys.getCreateTimes() != null){
                sql.append(" between '"+commoditys.getUpdateTime()+"' and '"+commoditys.getCreateTimes()+" ' ");
            }
        }
        return sql.toString();
    }
}
