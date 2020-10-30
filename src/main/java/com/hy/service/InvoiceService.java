package com.hy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Invoice;
import com.hy.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends ServiceImpl<InvoiceMapper,Invoice>{

}
