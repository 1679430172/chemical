package com.hy.mapper;

        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.baomidou.mybatisplus.core.metadata.IPage;
        import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        import com.hy.bean.Purchase;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Select;
        import org.apache.ibatis.annotations.Update;

        import java.util.List;

public interface PurchaseMapper extends BaseMapper<Purchase> {
    @Select("select * from purchase")
    public IPage<Purchase> Purchase(Page page);

    @Select("select * from purchase where =#{userId}")
    public List<Purchase> supplier();

    @Update("update purchase set ann = 2 where cid=#{cid}")
    public void updateAnn(String cid);


}
