package com.hy.util;

import com.hy.bean.Inventory;
import com.hy.bean.Purchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseSql {
    @Autowired
    private Purchase purchase;


    public String query(@Param("em")Purchase purchase){
        StringBuffer sql = null;
        if(purchase!=null&&! purchase.equals("")){

            sql = new StringBuffer("SELECT * FROM ( select cid,user_id,name,cas,amount,price,price_status,sum_price,status,supplier_name,supplier_phone,tracking_number,create_time,ann,b.user_name  from purchase a,users b where a.user_id=b.uid and ann=1 UNION all select cid,user_id,name,cas,amount,price,price_status,sum_price,status,supplier_name,supplier_phone,tracking_number,create_time,ann,b.user_name  from purchase a,users b where a.user_id=b.uid and ann in(2,3) ORDER BY ann asc,create_time desc)  aa where 1=1");
            if(purchase.getName() != null&&!"".equals(purchase.getName())){
                sql.append(" and name like '%"+purchase.getName()+"%'");
            }
            if (purchase.getCas() != null&&!"".equals(purchase.getCas())){
                sql.append(" and cas='"+purchase.getCas()+"'");
            }
            if (purchase.getSupplierName() != null&&!"".equals(purchase.getSupplierName())) {
                sql.append(" and supplier_name like '%" + purchase.getSupplierName()+"%'");
            }
            if (purchase.getTrackingNumber() != null&&!"".equals(purchase.getTrackingNumber())){
                sql.append(" and tracking_number like '%"+purchase.getTrackingNumber()+"%'");
            }

        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String bytrackingNumberselect(@Param("trackingNumber") String trackingNumber, @Param("name") String name, @Param("userId") Integer userId) {
        StringBuffer sql = new StringBuffer("select o.* ,c.amount,d.name from sales o , `order` c ,  commodity d WHERE o.order_id=c.did and c.commodity_id=d.sid ");
        if (userId != null && !"".equals(userId)) {
            sql.append(" and o.user_id =" + userId);
        }
        if (name != null && !"".equals(name)) {
            sql.append(" and d.name like '%" + name + "%'");
        }
        if (trackingNumber != null && !"".equals(trackingNumber)) {
            sql.append(" and o.tracking_number like '%" + trackingNumber + "%'");
        }
        return sql.toString();
    }

    public String selectListTime(@Param("stadate") String stadate, @Param("enddate") String enddate, @Param("name") String name,@Param("cname") String cname, @Param("userId") Integer userId) {
        StringBuffer sql = new StringBuffer("select o.*,c.user_id suid,c.name name,i.number,s.name cname,s.* from `order` o inner join commodity c on o.commodity_id=c.sid inner join inventory i on i.kid=o.invoice_id inner join client s on s.cid=o.cid where 1=1 ");
        if (userId != null && !"".equals(userId)) {
            sql.append(" and c.user_id=" + userId);
        }
        if (!"".equals(stadate)) {
            if(!"".equals(enddate)){
                sql.append(" and LEFT(o.create_time,10) between '" + stadate + "' and '" + enddate + "'");
            }
        }
        if (name != null && !"".equals(name)) {
            sql.append(" and c.name like  '%" + name + "%'");
        }
        if (cname != null && !"".equals(cname)) {
            sql.append(" and s.name like  '%" + cname + "%'");
        }
        sql.append(" order by o.create_time desc");
        return sql.toString();
    }

    public String queryBycass(@Param("name") String name, @Param("cas") String cas) {
        StringBuffer sql = new StringBuffer("select * from invoice where 1=1 ");
        if (cas != null && !"".equals(cas)) {
            sql.append(" and cas=" + cas);
        }
        if (name != null && !"".equals(name)) {
            sql.append(" and name like  '%" + name + "%'");
        }

        sql.append(" order by create_time desc");
        return sql.toString();
    }

}
