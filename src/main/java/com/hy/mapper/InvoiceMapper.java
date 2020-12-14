package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Invoice;
import com.hy.util.PurchaseSql;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface InvoiceMapper extends BaseMapper<Invoice> {
    @SelectProvider(type = PurchaseSql.class,method = "queryBycass")
    public IPage<Invoice> queryBycass(@Param("name") String name, @Param("cas") String cas, Page page);

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
