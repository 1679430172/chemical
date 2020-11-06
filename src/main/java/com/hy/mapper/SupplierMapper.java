package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
import com.hy.util.CommoditySql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    @SelectProvider(type = CommoditySql.class ,method = "supplier" )
    public IPage<SupplierUsers> supplier(Page page,@Param("uid")String uid);

    @Select("select * from supplier s inner join users u on s.user_id = u.uid")
    public List<SupplierUsers> supplier();
    @Select("select * from users")
    public List<SupplierUsers> users();
}
