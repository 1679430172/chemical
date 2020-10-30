package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.bean.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
