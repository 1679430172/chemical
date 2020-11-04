package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.User;
import org.apache.ibatis.annotations.*;

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

    /**
     * 修改业务员
     * @param user
     * @return
     */
    @Update("update users set password=#{password}, type=#{type},user_name=#{username} where  uid=#{id}")
    public void update(User user);

    /**
     * 查询所有授权类型
     * @return
     */
    @Select("select type from users where type not in (0)")
    public List<User> selcttype();

    /**
     * 插入业务员信息
     * @param user
     * @return
     */
    @Results({
            @Result(column = "uid",property = "id"),
    })
    @Insert("insert into users(user_name,password,type) values(#{username},#{password},#{type})")
    public void ttt(User user);


    /**
     * 查询所有账户
     * @return
     */
    @Results({
            @Result(column = "user_name",property = "username"),
    })
    @Select("select user_name from users ")
    public List<User> list();
}
