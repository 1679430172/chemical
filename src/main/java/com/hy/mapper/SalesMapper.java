package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Sales;
import com.hy.bean.SalesOrdet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;


@Mapper
public interface SalesMapper extends BaseMapper<Sales> {
    /**
     * 通过业务员权限查询退货订单
     * @param page
     * @param userId
     * @return
     */
    @Select("select o.*,c.`status` from sales o inner join `order` c on o.order_id=c.did where o.user_id=#{userId}")
    public IPage<SalesOrdet> selectSales(Page<SalesOrdet> page,@Param("userId")Integer userId);

    /**
     * 验证业务员权限查询退货订单(授权的业务员和管理员)
     * @param page
     * @return
     */
    @Select("select o.*,c.`status` from sales o inner join `order` c on o.order_id=c.did ")
    public IPage<SalesOrdet> selectSalestwo(Page<SalesOrdet> page);


    /**
     *通过订单编号查询订单
     * @param page
     * @return
     */
    @Select("select o.*,c.`status` from sales o inner join `order` c on o.order_id=c.did  where o.tracking_number=#{trackingNumber}")
    public IPage<SalesOrdet> bytrackingNumberselect(Page<SalesOrdet> page,@Param("trackingNumber")Integer trackingNumber);


    /**
     *通过订单编号查询订单
     * @param trackingNumber
     * @return
     */
    @Select("select o.*,c.`status` from sales o inner join `order` c on o.order_id=c.did where  o.tracking_number=#{trackingNumber} and o.user_id =#{userId}")
    public IPage<SalesOrdet> bytrackingNumberselecttwo(Page<SalesOrdet> page,@Param("trackingNumber")Integer trackingNumber,@Param("userId")Integer userId);

}
