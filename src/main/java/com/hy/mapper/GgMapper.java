package com.hy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hy.bean.Gg;
import org.apache.ibatis.annotations.Select;


public interface GgMapper extends BaseMapper<Gg> {
    @Select("select * from gg ORDER BY zt desc")
    public IPage<Gg> quergg(Page page);

}
