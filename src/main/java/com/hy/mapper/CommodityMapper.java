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

    @Update("update commodity set  price_info=#{commodity.priceInfo},update_time=#{commodity.updateTime} where sid=#{commodity.sid}")
    public void equals(@Param("commodity") Commodity commodity);

    @Update("update commodity set  img_path=#{commodity.imgPath},img_status =#{commodity.imgStatus} where sid=#{commodity.sid}")
    public void pictureEquals(@Param("commodity") Commodity commodity);

    @Update("update commodity set  file_status=#{commodity.fileStatus},file_path=#{commodity.filePath} where sid=#{commodity.sid}")
    public void paper(@Param("commodity") Commodity commodity);

    @Select("select * from commodity where supplier_id=#{supplierId}")
    public List<Commodity> suppliers(@Param("supplierId") String supplierId);

    @Select("select * from commodity where supplier_id=#{supplierId} and cas=#{cas}")
    public List<Commodity> supplierss(@Param("supplierId") String supplierId,@Param("cas") String cas);
}
