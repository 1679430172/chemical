package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Sales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SalesMapper extends BaseMapper<Sales> {
    /**
     * 通过业务员权限查询退货订单
     * @param page
     * @param userId
     * @return
     */
    @Select("select * from sales where  user_id=#{userId} ")
    public IPage<Sales> selectSales(Page<Sales> page,@Param("userId")Integer userId);

    /**
     * 验证业务员权限查询退货订单(授权的业务员和管理员)
     * @param page
     * @return
     */
    @Select("select * from sales ")
    public IPage<Sales> selectSalestwo(Page<Sales> page);


    /**
     *通过订单编号查询订单
     * @param page
     * @return
     */
    @Select("select * from sales  where tracking_number=#{trackingNumber}")
    public IPage<Sales> bytrackingNumberselect(Page<Sales> page,@Param("trackingNumber")Integer trackingNumber);


    /**
     *通过订单编号查询订单
     * @param trackingNumber
     * @return
     */
    @Select("select * from sales where  tracking_number=#{trackingNumber} and user_id =#{userId}")
    public IPage<Sales> bytrackingNumberselecttwo(Page<Sales> page,@Param("trackingNumber")Integer trackingNumber,@Param("userId")Integer userId);

}
