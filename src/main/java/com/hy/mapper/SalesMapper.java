package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Sales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SalesMapper extends BaseMapper<Sales> {
    /**
     * 验证业务员权限查询退货订单
     * @param page
     * @param userId
     * @return
     */
    @Select("select * from sales where  user_id=#{userId} ")
    public IPage<Sales> selectSales(Page<Sales> page,@Param("userId")Integer userId);

}
