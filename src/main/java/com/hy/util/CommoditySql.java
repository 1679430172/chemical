package com.hy.util;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.util.SystemOutLogger;

public class CommoditySql {

    public String CommoditySql(@Param("commoditys")Commoditys commoditys, @Param("supplierId") String sid){
        StringBuffer sql=new StringBuffer("SELECT * FROM (SELECT c.sid, c.`name`,c.cas,c.commodity_info ,\n" +
                "                u.uid,u.user_name,price_info,c.create_time,c.update_time,c.supplier_id,i.amount,c.img_status,c.img_path,c.file_status,c.file_path \n" +
                "               FROM commodity c  left join inventory i on c.cas = i.cas LEFT JOIN users u on  c.user_id=u.uid  \n" +
                "                left join supplier s on c.supplier_id=s.gid where i.amount is not null )\n" +
                "                 aaa\n" +
                " UNION all\n" +
                " SELECT * FROM (SELECT c.sid, c.`name`,c.cas,c.commodity_info ,\n" +
                "                u.uid,u.user_name,price_info,c.create_time,c.update_time,c.supplier_id,i.amount,c.img_status,c.img_path,c.file_status,c.file_path \n" +
                "               FROM commodity c  left join inventory i on c.cas = i.cas LEFT JOIN users u on  c.user_id=u.uid  \n" +
                "                left join supplier s on c.supplier_id=s.gid  where i.amount is null  ) bb where 1=1 " );
        if (commoditys.getCreateTime() != null && commoditys.getCreateTimes() != null && !"".equals(commoditys.getCreateTime()) && !"".equals(commoditys.getCreateTimes())) {
            sql.append(" and update_time between '" + commoditys.getCreateTime() + "' and '" + commoditys.getCreateTimes() + "' ");
        }
        if (commoditys.getName() != null && !"".equals(commoditys.getName())) {
            sql.append(" and name like '%" + commoditys.getName()+"%'");
        }
        if (commoditys.getCas() != null && !"".equals(commoditys.getCas())) {
            sql.append(" and cas = '" + commoditys.getCas()+"'");
        }
        /*if(sid != null && !"".equals(sid) ){
            sql.append(" and user_id ="+sid);
        }*/
        sql.append(" ORDER BY amount desc,update_time desc");
        return sql.toString();
    }

    public String sql(@Param("sid")String sid,@Param("priceInfo") String priceInfo){
        StringBuffer sql=new StringBuffer("update commodity set price_info=#{priceInfo} where  sid=#{id}");
        return sql.toString();
    }

    public String supplier(@Param("names") SupplierUsers names){
        String sql="select * from supplier s inner join users u on s.user_id = u.uid where 1=1 ";
        if ( names.getUid() != null  ) {
            sql+=" and uid="+names.getUid();
        }
        if (names.getName() != null && !"".equals(names.getName())) {
            sql += " and s.name like '%" + names.getName() + "%'";
        }
        if (names.getUserName() != null && !"".equals(names.getUserName())) {
            sql += " and user_name like '%" + names.getUserName() + "%'";
        }
        sql+=" order by s.gid  ";
        return sql;
    }

    public String equals(@Param("commodity")Commodity commodity){
        String sql="update commodity set commodity_info=#{commodity.commodityInfo} , price_info=#{commodity.priceInfo},name=#{commodity.name},cas=#{commodity.cas} ";
        if(commodity.getImgPath() != null){
            sql+=" ,img_path='"+commodity.getImgPath()+"'";
        }
        if(commodity.getImgStatus() != null){
            sql+=" ,img_status="+commodity.getImgStatus();
        }
        if(commodity.getFileStatus() != null){
            sql+=" ,file_status="+commodity.getFileStatus();
        }
        if(commodity.getFilePath() != null){
            sql+=" ,file_path='"+commodity.getFilePath()+"'";
        }
        if(commodity.getCommodityInfo() != null){
            sql+=" ,commodity_info='"+commodity.getCommodityInfo()+"'";
        }
        sql+="  where  sid="+commodity.getSid();
        return sql;
    }

    public String supplierli(@Param("supplier") Supplier supplier){
        String sql="select * from supplier s , users u where s.user_id = u.uid  ";
        if(supplier.getGid() != null){
            sql+=" and s.gid = "+supplier.getGid();
        }
        if(supplier.getName() != null){
            sql+="  and s.name like '%"+supplier.getName()+"%'";
        }
        return sql;
    }
}
