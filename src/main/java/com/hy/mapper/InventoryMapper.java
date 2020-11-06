package com.hy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Inventory;
import com.hy.util.InventorySql;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface InventoryMapper extends BaseMapper<Inventory> {
    @Select("select * from inventory")
    public IPage<Inventory> Inventory(Page page);

    @SelectProvider(type = InventorySql.class,method = "query")
    public List<Inventory> queryBy(Page page, @Param("em")Inventory inventory);




}
