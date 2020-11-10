package com.hy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.*;
import com.hy.mapper.CommodityMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
    @Autowired
    private CommodityMapper commodityMapper;

    public IPage<Commoditys> CommditysList(Integer page, Integer limit,Commoditys commoditys){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        Integer userId= (Integer) session.getAttribute("userId");
        IPage<Commoditys> iPage=null;

        /*if(userType.equals("0") || userType.equals("2")){
            commodityMapper.CommditysList(new Page(page,limit),commoditys,null);
            return iPage;
        }else if(userType == "1"){
            commodityMapper.CommditysList(new Page(page,limit),commoditys,String.valueOf(userId));
            return iPage;
        }*/
        return commodityMapper.CommditysList(new Page(page,limit),commoditys,String.valueOf(userId));
    }

    public Commodity byid(String sid){
        return commodityMapper.byid(sid);
    }

    public void   equals(Commodity commodity){
        commodityMapper.equals(commodity);
    }



    public void pictures(MultipartFile pictureFile) throws IOException {
        String picName = UUID.randomUUID().toString();// 设置图片名称，不能重复，可以使用uuid
        String oriName = pictureFile.getOriginalFilename();//获取文件名
        String extName = oriName.substring(oriName.lastIndexOf("."));//// 获取图片后缀
        File file=new File("/assets/" + picName + extName);
        if (!file.exists()) {
            file.mkdirs();
        }

        FileUtils.copyInputStreamToFile(pictureFile.getInputStream(),file);
        boolean isCreateSuccess = file.createNewFile(); // 是否创建文件成功
        if(isCreateSuccess){      //将文件写入
            //第一种
            //file.transferTo(savedFile);
            //第二种
            System.out.println("---------------"+isCreateSuccess);
        }
        Commodity commodity=new Commodity();
        commodity.setImgPath(file.toString());
        pictureFile.transferTo(file);
        // 判断文件父目录是否存在
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

         commodityMapper.equals(commodity);
    }
}
