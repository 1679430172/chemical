package com.hy.mapper;

        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.baomidou.mybatisplus.core.metadata.IPage;
        import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        import com.hy.bean.Commodity;
        import com.hy.bean.Inventory;
        import com.hy.bean.Purchase;
        import com.hy.util.InventorySql;
        import com.hy.util.PurchaseSql;
        import org.apache.ibatis.annotations.*;

        import java.util.List;

public interface PurchaseMapper extends BaseMapper<Purchase> {
    @Select("select * from purchase")
    public IPage<Purchase> Purchase(Page page);

    @Select("select * from purchase where userId=#{userId}")
    public IPage<Purchase> supplier(@Param("userId") Integer userId,Page page);

    @Update("update purchase set ann = 2 where cid=#{cid}")
    public void updateAnn(String cid);

    @Update("update purchase set tracking_number = #{trackingNumber} where cid=#{cid}")
    public void  updateTN(String trackingNumber);

    @Update("update purchase set  tracking_number=#{purchase.trackingNumber}where cid=#{purchase.cid}")
    public void equals(@Param("purchase") Purchase purchase);

    @Insert("insert into purchase(cid,user_id,name,cas,amount,price,price_status,sum_price,status,user_name,supplier_name,supplier_phone,tracking_number,create_time,ann) values(#{cid},#{userId},#{name},#{cas},#{amount},#{price},#{priceStatus},#{sumPrice},#{status},#{userName},#{supplierName},#{supplierPhone},#{trackingNumber},now(),1)")
    public int addPurchase(Purchase purchase);

    @SelectProvider(type = PurchaseSql.class,method = "query")
    public IPage<Purchase> queryBy(Page page, @Param("em")Purchase purchase);


}
