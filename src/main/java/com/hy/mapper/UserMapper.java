package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询业务员名字
     * @param user
     * @return
     */
    @Select("select * from users where  user_name=#{username} and password=#{password}")
    public User select(User user);

    /**
     * 权限查询和分页
     * @return
     */
    @Results({
            @Result(column = "uid",property = "id"),
    })
    @Select("select * from users where type not in (0)")
    public IPage<User> selectlist(Page<User> page);

    /**
     * 通过id查询业务员
     * @param id
     * @return
     */
    @Results({
            @Result(column = "uid",property = "id"),
    })
    @Select("select * from users where  uid=#{id}")
    public User selectbyid(Integer id);


    @Select("update users set password=#{password}, type=#{type},user_name=#{username} where  uid=#{id}")
    public Void update(User user);

}
