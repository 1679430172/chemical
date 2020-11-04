package com.hy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Invoice;
import com.hy.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService extends ServiceImpl<InvoiceMapper,Invoice>{
    @Autowired
    private InvoiceMapper invoiceMapper;

    public List<Invoice> queryByCas(String cas){
        return invoiceMapper.queryBycas(cas);

    }

}
