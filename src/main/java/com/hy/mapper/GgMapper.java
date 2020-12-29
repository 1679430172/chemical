package com.hy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hy.bean.Gg;
import org.apache.ibatis.annotations.*;


public interface GgMapper extends BaseMapper<Gg> {
    @Select("select * from gg ORDER BY zt desc")
    public IPage<Gg> quergg(Page page);


    @Insert("insert into gg(bt,nr,fjr,zt,sj) values(#{bt},#{nr},#{fjr},#{zt},now())")
    public int addgg(Gg gg);

    @Delete("delete from gg where id=#{id}")
    public Gg detelep(@Param("id")Integer id);

    @Update("update gg set bt=#{bt},nr=#{nr},fjr=#{fjr},zt=#{zt},create_time=now()")
    public boolean autoUpdate(@Param("id")Integer id);
}
