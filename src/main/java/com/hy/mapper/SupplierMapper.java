package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
import com.hy.util.CommoditySql;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    @SelectProvider(type = CommoditySql.class ,method = "supplier" )
    public IPage<SupplierUsers> suppliers(Page page,@Param("uid")String uid,@Param("names")String names);

    @Select("select * from supplier s , users u where  s.user_id = u.uid order by s.user_id ")
    public List<SupplierUsers> supplier();

    @Select("select * from users")
    public List<SupplierUsers> users();

    @Update("update supplier set user_id=#{supplier.userId},name=#{supplier.name},phone=#{supplier.phone},wechat=#{supplier.wechat},status=#{supplier.status},create_time=#{supplier.createTime},remark=#{supplier.remark}  where gid=#{supplier.gid}")
    public Integer update(@Param("supplier") Supplier supplier);

    @Delete("DELETE FROM supplier where gid=#{gid} ")
    public Integer delete(String gid);

    @SelectProvider(type = CommoditySql.class ,method = "supplierli" )
    public List<SupplierUsers> supplierlist(@Param("supplier")Supplier supplier);
}
