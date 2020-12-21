package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.bean.Sales;
import com.hy.bean.SalesOrdet;
import com.hy.util.PurchaseSql;
import org.apache.ibatis.annotations.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;


@Mapper
public interface SalesMapper extends BaseMapper<Sales> {
    /**
     * 通过业务员权限查询退货订单
     * @param page
     * @param userId
     * @return
     */
    @Select("select o.*,c.amount ,d.name from sales o , `order` c ,  commodity d WHERE o.order_id=c.did and c.commodity_id=d.sid and o.user_id=#{userId}")
    public IPage<SalesOrdet> selectSales(Page<SalesOrdet> page,@Param("userId")Integer userId);

    /**
     * 验证业务员权限查询退货订单(授权的业务员和管理员)
     * @param page
     * @return
     */
    @Select("select o.*,c.amount ,d.name,c.invoice_id from sales o , `order` c ,  commodity d WHERE o.order_id=c.did and c.commodity_id=d.sid   ")
    public IPage<SalesOrdet> selectSalestwo(Page<SalesOrdet> page);


    /**
     *通过订单编号查询订单
     * @param page
     * @return
     */
    @SelectProvider(type = PurchaseSql.class,method = "bytrackingNumberselect")
    public IPage<SalesOrdet> bytrackingNumberselect(Page<SalesOrdet> page,@Param("trackingNumber")String trackingNumber,@Param("name")String name,@Param("userId")Integer userId);


    /**
     *通过订单编号查询订单
     * @param trackingNumber
     * @return
     */
    @Select("select o.*,c.`status`,c.amount ,d.name from sales o , `order` c ,  commodity d WHERE o.order_id=c.did and c.commodity_id=d.sid and (o.tracking_number=#{trackingNumber} or d.name=#{trackingNumber}) and o.user_id =#{userId}")
    public IPage<SalesOrdet> bytrackingNumberselecttwo(Page<SalesOrdet> page,@Param("trackingNumber")String trackingNumber,@Param("userId")Integer userId);

    /**
     * 通过订单id修改退货单号
     * @param trackingNumber
     * @param orderId
     * @return
     */
    @Update("update sales set tracking_number=#{trackingNumber} where did=#{orderId}")
    public Integer updatetrackingNumber(@Param("trackingNumber")String trackingNumber,@Param("orderId")Integer orderId);


}
