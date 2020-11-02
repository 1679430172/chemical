package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import org.apache.ibatis.annotations.Select;

public interface SupplierMapper extends BaseMapper<Supplier> {

    @Select("select * from supplier s inner join users u on s.user_id = u.uid")
    public IPage<SupplierUsers> supplier(Page page);
}
