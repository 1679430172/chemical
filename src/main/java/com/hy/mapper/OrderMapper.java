package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Results({
            @Result(column = "name",property = "name"),
            @Result(column = "c.user_id",property = "suid")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid where c.user_id=#{userId} or o.user_id=#{userId}")
    public IPage<Order> selectListByUserId(@Param("userId") Integer userId,Page page);

    @Results({
            @Result(column = "name",property = "name"),
            @Result(column = "c.user_id",property = "suid")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid")
    public IPage<Order> selectList(Page page);

    @Results({
            @Result(column = "name",property = "name")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid where o.user_id=#{userId} and status = 1 and did not in (select order_id from sales)")
    public IPage<Order> selectListByStatus(@Param("userId") Integer userId,Page page);

    @Results({
            @Result(column = "name",property = "name")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid where status = 1 and did not in (select order_id from sales)")
    public IPage<Order> selectListByStatus(Page page);

    @Results({
            @Result(column = "name",property = "name")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid where status = 1 and did not in (select order_id from sales)")
    public IPage<Order> selectStatus(Integer userId);

}
