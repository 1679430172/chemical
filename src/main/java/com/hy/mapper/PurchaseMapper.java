package com.hy.mapper;

        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.baomidou.mybatisplus.core.metadata.IPage;
        import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        import com.hy.bean.Inventory;
        import com.hy.bean.Purchase;
        import com.hy.util.PurchaseSql;
        import org.apache.ibatis.annotations.*;

public interface PurchaseMapper extends BaseMapper<Purchase> {
    @Select("select cid,user_id,name,cas,amount,price,price_status,sum_price,status,supplier_name,supplier_phone,tracking_number,create_time,ann,b.user_name  from purchase a,users b where a.user_id=b.uid and ann=1 UNION all select cid,user_id,name,cas,amount,price,price_status,sum_price,status,supplier_name,supplier_phone,tracking_number,create_time,ann,b.user_name  from purchase a,users b where a.user_id=b.uid and ann in(2,3) ORDER BY ann asc,create_time desc")
    public IPage<Purchase> Purchase(Page page);

    @Select("select * from purchase where cid=#{cid}")
    public Purchase selectBycid(Integer cid);


    @Select("SELECT * FROM ( select cid,user_id,name,cas,amount,price,price_status,sum_price,status,supplier_name,supplier_phone,tracking_number,create_time,ann,b.user_name  from purchase a,users b where a.user_id=b.uid and ann=1 UNION all select cid,user_id,name,cas,amount,price,price_status,sum_price,status,supplier_name,supplier_phone,tracking_number,create_time,ann,b.user_name  from purchase a,users b where a.user_id=b.uid and ann in(2,3) ORDER BY ann asc,create_time desc)  aa WHERE user_id=#{userId}")
    public IPage<Purchase> supplier(@Param("userId") Integer userId,Page page);

    @Update("update purchase set ann = 2 where cid=#{cid}")
    public void updateAnn(String cid);

    @Update("update purchase set tracking_number = #{trackingNumber} where cid=#{cid}")
    public void  updateTN(@Param("trackingNumber")String trackingNumber,@Param("cid")String cid);

    @Update("update purchase set  name=#{name},cas=#{cas},amount=#{amount},price=#{price}where cid=#{purchase.cid}")
    public boolean updatea(@Param("purchase") Purchase purchase);

    @Insert("insert into purchase(cid,user_id,name,cas,amount,price,price_status,sum_price,status,user_name,supplier_name,supplier_phone,tracking_number,create_time,ann) values(#{cid},#{userId},#{name},#{cas},#{amount},#{price},#{priceStatus},#{sumPrice},#{status},#{userName},#{supplierName},#{supplierPhone},#{trackingNumber},now(),1)")
    public int addPurchase(Purchase purchase);

    @Insert("insert into purchase(cid,user_id,name,cas,amount,price,price_status,sum_price,status,user_name,supplier_name,supplier_phone,tracking_number,create_time,ann) values(#{cid},#{userId},#{name},#{cas},#{amount},#{price},#{priceStatus},#{sumPrice},#{status},#{userName},#{supplierName},#{supplierPhone},#{trackingNumber},now(),3)")
    public int adPurchase(Purchase purchase);

    @SelectProvider(type = PurchaseSql.class,method = "query")
    public IPage<Purchase> queryBy(Page page, @Param("em")Purchase purchase);

    @Delete("delete from purchase where cid=#{cid}")
    public Inventory detelep(@Param("cid")String number);

}
