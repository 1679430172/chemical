package com.hy.util;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import org.apache.ibatis.annotations.Param;

public class CommoditySql {

    public String CommoditySql(@Param("commoditys")Commoditys commoditys){
        StringBuffer sql=new StringBuffer("SELECT c.sid, c.`name`,i.cas ,u.user_name,price_info,c.create_time,c.update_time,c.supplier_id,i.amount \n" +
                "FROM commodity c left join supplier s on c.supplier_id=s.gid left join inventory i on c.cas = i.cas LEFT JOIN users u on  c.user_id=u.uid\n" +
                "where 1=1 ");
            if(commoditys.getCreateTime() !=null && commoditys.getCreateTimes() != null){
                sql.append(" and c.create_time between '"+commoditys.getCreateTime()+"' and '"+commoditys.getCreateTimes()+" ' ");
            }
            sql.append("order by i.amount desc ");
            System.out.println(sql);
        return sql.toString();
    }

    public String sql(@Param("sid")String sid,@Param("priceInfo") String priceInfo){
        StringBuffer sql=new StringBuffer("update commodity set price_info=#{priceInfo} where  sid=#{id}");
        return sql.toString();
    }
}
