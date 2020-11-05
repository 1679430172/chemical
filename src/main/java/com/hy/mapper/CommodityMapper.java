package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.Order;
import com.hy.bean.SupplierUsers;
import com.hy.util.CommoditySql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CommodityMapper  extends BaseMapper<Commodity> {

    @SelectProvider(type = CommoditySql.class ,method = "CommoditySql" )
    IPage<Commoditys> CommditysList(Page page, @Param("commoditys") Commoditys commoditys);

    @Select("select * from commodity where  sid=#{sid}")
    public Commodity byid(String sid);

    @Select("update commodity set price_info=#{priceInfo} where  sid=#{sid}")
    public String equals(@Param("sid")Integer sid,@Param("priceInfo") String priceInfo);
}
