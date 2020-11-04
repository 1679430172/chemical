package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Results({
            @Result(column = "name",property = "name")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid where c.user_id=#{userId} and o.user_id=#{userId}")
    public IPage<Order> selectListByUserId(@Param("userId") Integer userId,Page page);

    @Results({
            @Result(column = "name",property = "name")
    })
    @Select("select * from `order` o inner join commodity c on o.commodity_id=c.sid")
    public IPage<Order> selectList(Page page);
}
