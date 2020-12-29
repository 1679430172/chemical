package com.hy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hy.bean.Gg;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface GgMapper extends BaseMapper<Gg> {
    @Select("select * from gg ORDER BY zt desc")
    public IPage<Gg> quergg(Page page);


    @Select("select * from gg where gg.zt='1'")
    public List<Gg> list();

    @Insert("insert into gg(bt,nr,fjr,zt,create_time) values(#{gg.bt},#{gg.nr},#{gg.fjr},2,now())")
    public int addgg(@Param("gg") Gg gg);

    @Delete("delete from gg where id=#{id}")
    public Gg detelep(@Param("id")Integer id);


    @Update("update gg set zt=2 where id=#{id}")
    public boolean UpdateId(@Param("id")Integer id);

    @Update("update gg set zt=1 where id=#{id}")
    public boolean UpdatId(@Param("id")Integer id);

    @Update("update gg set bt=#{bt},nr=#{nr},fjr=#{fjr},zt=2,create_time=now()")
    public boolean autoUpdate(@Param("id")Integer id);
}
