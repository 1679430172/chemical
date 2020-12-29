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

<<<<<<< HEAD
    @Select("select * from gg where gg.zt='1'")
    public List<Gg> list();
=======

    @Insert("insert into gg(bt,nr,fjr,zt,sj) values(#{bt},#{nr},#{fjr},#{zt},now())")
    public int addgg(Gg gg);

    @Delete("delete from gg where id=#{id}")
    public Gg detelep(@Param("id")Integer id);

    @Update("update gg set bt=#{bt},nr=#{nr},fjr=#{fjr},zt=#{zt},create_time=now()")
    public boolean autoUpdate(@Param("id")Integer id);
>>>>>>> b738226a43824aae2134a62e2973ed459b6344e9
}
