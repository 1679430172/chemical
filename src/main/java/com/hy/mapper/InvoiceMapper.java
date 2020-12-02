package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.bean.Invoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface InvoiceMapper extends BaseMapper<Invoice> {
    @Select("select * from invoice where cas=#{cas}")
    public List<Invoice> queryBycas(String cas);

    @Select("select * from invoice where sid=#{sid}")
    public Invoice queryBysid(Integer sid);

    @Update("update invoice set number=#{number} where sid=#{sid}")
    public boolean update(@Param("number") Double number, @Param("sid")Integer sid);

    @Update("update invoice set number=number+#{number} where sid=#{sid}")
    public boolean autoUpdate(@Param("number") Double number, @Param("sid")Integer sid);

    @Update("update invoice set name =#{invoice.name},cas=#{invoice.cas} ,number=#{invoice.number}," +
            "price=#{invoice.price},unit=#{invoice.unit} where sid=#{invoice.sid}")
    public boolean updatesid(@Param("invoice")Invoice invoice);

}
