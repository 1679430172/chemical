package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 根据业务员id查询所有订单
     * @return
     */
    @Select("select o.*,c.user_id suid,c.name name,i.number from `order` o inner join commodity c on o.commodity_id=c.sid inner join inventory i on i.kid=o.invoice_id where c.user_id=#{userId} or o.user_id=#{userId}")
    public IPage<Order> selectListByUserId(@Param("userId") Integer userId,Page page);

    /**
     * 查询所有订单
     * @return
     */
    @Select("select o.*,c.user_id suid,c.name name,i.number from `order` o inner join commodity c on o.commodity_id=c.sid inner join inventory i on i.kid=o.invoice_id")
    public IPage<Order> selectList(Page page);

    /**
     * 根据业务员id查询是否出库
     * @return
     */
    @Select("select o.*,c.user_id suid,c.name name from `order` o inner join commodity c on o.commodity_id=c.sid where o.user_id=#{userId} and `status` = '1' and did not in (select order_id from sales)")
    public IPage<Order> selectListByStatust(@Param("userId") Integer userId,Page page);

    /**
     * 查询所有出库状态
     * @return
     */
    @Select("select o.*,c.user_id suid,c.name name from `order` o inner join commodity c on o.commodity_id=c.sid where `status` = '1' and did not in (select order_id from sales)")
    public IPage<Order> selectListByStatus(Page page);

    /**
     * 修改为出库状态
     * @return
     */
    @Update("update `order` set status='1' where did=#{value}")
    public Integer updateStatus(Integer did);

    /**
     * 修改为退货状态
     * @return
     */
    @Update("update `order` set status='2' where did=#{did}")
    public Integer updateOrder(@Param("did")Integer did);

    /**
     * 根据业务员id查询某时间所有订单
     * @return
     */
    @Select("select o.*,c.user_id suid,c.name name,i.number from `order` o inner join commodity c on o.commodity_id=c.sid inner join inventory i on i.kid=o.invoice_id where c.user_id=#{userId} or o.user_id=#{userId} and c.name like ${name} and LEFT(o.create_time,10) between #{stadate} and #{enddate}")
    public IPage<Order> selectListByUserIdTime(@Param("stadate") String stadate,@Param("enddate") String enddate,@Param("name") String name,@Param("userId") Integer userId,Page page);

    /**
     * 查询某时间所有订单
     * @return
     */
    @Select("select o.*,c.user_id suid,c.name name,i.number from `order` o inner join commodity c on o.commodity_id=c.sid inner join inventory i on i.kid=o.invoice_id where c.name like ${name} and LEFT(o.create_time,10) between #{stadate} and #{enddate}")
    public IPage<Order> selectListTime(@Param("stadate") String stadate,@Param("enddate") String enddate,@Param("name") String name, Page page);
}
