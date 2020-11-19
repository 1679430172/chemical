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

    @Update("update invoice set number=#{number} where sid=#{sid}")
    public boolean update(@Param("number") Integer number, @Param("sid")Integer sid);

    @Update("update invoice set number=number+#{number} where sid=#{sid}")
    public boolean autoUpdate(@Param("number") Integer number, @Param("sid")Integer sid);

}
