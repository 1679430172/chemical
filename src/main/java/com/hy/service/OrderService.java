package com.hy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Order;
import com.hy.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

}
