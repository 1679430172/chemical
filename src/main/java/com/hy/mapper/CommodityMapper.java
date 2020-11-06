package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.Order;
import com.hy.bean.SupplierUsers;
import com.hy.util.CommoditySql;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommodityMapper  extends BaseMapper<Commodity> {

    @SelectProvider(type = CommoditySql.class ,method = "CommoditySql" )
    IPage<Commoditys> CommditysList(Page page, @Param("commoditys") Commoditys commoditys,@Param("supplierId") String supplierId);

    @Select("select * from commodity where  sid=#{sid}")
    public Commodity byid(String sid);

    @Update("<script>" +
            " update commodity" +
            " <set>" +
            " <if test='commodity.priceInfo != null'>" +
            "  price_info = #{commodity.priceInfo}," +
            " </if>" +
            " <if test='commodity.imgPath != null'>" +
            "  img_path=#{commodity.imgPath}," +
            " </if>" +
            " <if test='commodity.filePath != null'>" +
            "  file_path=#{commodity.filePath}," +
            " </if>" +
            " </set>" +
            " where sid = commodity.sid " +
            " </script>")
    public void equals(@Param("commodity") Commodity commodity);

}
