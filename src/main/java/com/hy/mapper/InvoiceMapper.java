package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.bean.Invoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface InvoiceMapper extends BaseMapper<Invoice> {
    @Select("select * from Invoice where cas=#{cas}")
    public List<Invoice> queryBycas(String cas);


}
