package com.hy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Inventory;
import org.apache.ibatis.annotations.Select;

public interface InventoryMapper extends BaseMapper<Inventory> {
    @Select("select * from inventory")
    public IPage<Inventory> Inventory(Page page);

}
