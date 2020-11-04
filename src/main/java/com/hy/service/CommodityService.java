package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.mapper.CommodityMapper;
import com.hy.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
    @Autowired
    private CommodityMapper commodityMapper;

    public IPage<Commoditys> CommditysList(Integer page, Integer limit,String createTime,String createTimes){
        return commodityMapper.CommditysList(new Page(page,limit),createTime,createTimes);
    }

    public Commodity pictures(MultipartFile pictureFile) throws IOException {
        String picName = UUID.randomUUID().toString();// 设置图片名称，不能重复，可以使用uuid
        String oriName = pictureFile.getOriginalFilename();//获取文件名
        String extName = oriName.substring(oriName.lastIndexOf("."));//// 获取图片后缀
        File file=new File("/web/files/" + picName + extName);

        Commodity commodity=new Commodity();
        commodity.setImgPath(file.toString()+"----------");
        pictureFile.transferTo(file);
        // 判断文件父目录是否存在
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        return null;
    }

}
