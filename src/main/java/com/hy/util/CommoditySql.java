package com.hy.util;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import org.apache.ibatis.annotations.Param;

public class CommoditySql {

    public String CommoditySql(@Param("commoditys")Commoditys commoditys, @Param("supplierId") String sid){
        StringBuffer sql=new StringBuffer("SELECT c.sid, c.`name`,c.cas ,u.uid,u.user_name,price_info,c.create_time,c.update_time,c.supplier_id,i.amount,c.img_status,c.img_path,c.file_status,c.file_path " +
                "FROM commodity c left join supplier s on c.supplier_id=s.gid left join inventory i on c.cas = i.cas LEFT JOIN users u on  c.user_id=u.uid " +
                "where 1=1 ");
        if(commoditys.getCreateTime() !=null && commoditys.getCreateTimes() != null){
            sql.append(" and c.update_time between ' "+commoditys.getCreateTime()+" ' and '"+commoditys.getCreateTimes()+" ' ");
        }
        if (commoditys.getName() != null && !"".equals(commoditys.getName())) {
            sql.append(" and c.name like '%" + commoditys.getName()+" % '");
        }
        if (commoditys.getCas() != null && !"".equals(commoditys.getCas())) {
            sql.append(" and c.cas = '" + commoditys.getCas()+"'");
        }
        /*if(sid != null && !"".equals(sid) ){
            sql.append(" and c.user_id ="+sid);
        }*/
        sql.append(" order by i.amount desc ");
        return sql.toString();
    }

    public String sql(@Param("sid")String sid,@Param("priceInfo") String priceInfo){
        StringBuffer sql=new StringBuffer("update commodity set price_info=#{priceInfo} where  sid=#{id}");
        return sql.toString();
    }

    public String supplier( @Param("uid")String uid,@Param("names")String names){
        String sql="select * from supplier s inner join users u on s.user_id = u.uid where 1=1 ";
        if ( uid != null  ) {
            sql+=" and uid="+uid;
        }
        if (names != null && !"".equals(names)) {
            sql += " and s.name like '%" + names + "%'";
        }
        return sql;
    }

    public String equals(@Param("commodity")Commodity commodity){
        String sql="update commodity set  price_info=#{commodity.priceInfo},name=#{commodity.name},cas=#{commodity.cas},user_id=#{commodity.userId}" +
                ", supplier_id=#{commodity.supplierId} ";
        if(commodity.getImgPath() != null){
            sql+=" ,img_path="+commodity.getImgPath();
        }
        if(commodity.getImgStatus() != null){
            sql+=" ,img_status="+commodity.getImgStatus();
        }
        if(commodity.getFileStatus() != null){
            sql+=" ,file_status="+commodity.getFileStatus();
        }
        if(commodity.getFilePath() != null){
            sql+=" ,file_path="+commodity.getFilePath();
        }
        sql+="  where  sid="+commodity.getSid();
        return sql;
    }
}
