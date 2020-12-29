package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.bean.upload;
import com.hy.util.CommoditySql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface uploadMapper extends BaseMapper<upload>{

    @Select("SELECT * FROM upload u,users us where u.uid = us.uid ")
    public List<upload> upload();

    @Select("SELECT * FROM upload u,users us where u.uid = us.uid and u.uid=#{id}")
    public List<upload> uploads(Integer id);

}
