package com.hy.util;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import org.apache.ibatis.annotations.Param;

public class CommoditySql {

    public String CommoditySql(@Param("commoditys")Commoditys commoditys, @Param("supplierId") String sid){
        StringBuffer sql=new StringBuffer("SELECT c.sid, c.`name`,i.cas ,u.uid,u.user_name,price_info,c.create_time,c.update_time,c.supplier_id,i.amount,c.img_status,c.img_path,c.file_status,c.file_path " +
                "FROM commodity c left join supplier s on c.supplier_id=s.gid left join inventory i on c.cas = i.cas LEFT JOIN users u on  c.user_id=u.uid " +
                "where 1=1 ");
            if(commoditys.getCreateTime() !=null && commoditys.getCreateTimes() != null){
                sql.append(" and c.create_time between ' "+commoditys.getCreateTime()+" ' and '"+commoditys.getCreateTimes()+" ' ");
            }
            if(sid != null && sid.equals("") ){
                sql.append(" and c.user_id ="+sid);
            }
            sql.append(" order by i.amount desc ");
        return sql.toString();
    }

    public String sql(@Param("sid")String sid,@Param("priceInfo") String priceInfo){
        StringBuffer sql=new StringBuffer("update commodity set price_info=#{priceInfo} where  sid=#{id}");
        return sql.toString();
    }

    public String supplier( @Param("uid")String uid){
        String sql="select * from supplier s inner join users u on s.user_id = u.uid where 1=1 ";
        if ( uid != null  ) {
            sql+=" and uid="+uid;
        }
        return sql;
    }

   /* public String Update(@Param("commodity")Commodity commodity){
        String sql="update commodity set  price_info=#{priceInfo}  ";
        if(commodity.getPriceInfo() != null){
            sql+=" price_info";
        }

        sql+="where  sid="+commodity.getSid();

        return sql;
    }*/
}
