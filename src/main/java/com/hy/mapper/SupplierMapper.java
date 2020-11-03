package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    @Select("select * from supplier s inner join users u on s.user_id = u.uid")
    public IPage<SupplierUsers> supplier(Page page);

    @Select("select * from supplier s inner join users u on s.user_id = u.uid")
    public List<SupplierUsers> supplier();
    @Select("select * from users")
    public List<User> users();
}
