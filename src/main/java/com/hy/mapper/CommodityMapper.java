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
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
@Mapper
public interface CommodityMapper  extends BaseMapper<Commodity> {

    @SelectProvider(type = CommoditySql.class ,method = "CommoditySql" )
    public IPage<Commoditys> CommditysList(Page page, @Param("createTime")String createTime,@Param("createTimes")String createTimes);
}
