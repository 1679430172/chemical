package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.SupplierUsers;
import com.hy.service.CommodityService;
import com.hy.service.SupplierService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Api
@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @RequestMapping("/Commoditys.do")
    @ResponseBody
    public ParseData  Commoditys(Integer page, Integer limit, String createTime,String createTimes){
        IPage<Commoditys> iPage=  commodityService.CommditysList(page,limit,createTime,createTimes);
        System.out.println(createTime+"--------"+createTimes);
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    @RequestMapping("/pictures.do")
    @ResponseBody
    public String pictures(@RequestParam("file") MultipartFile pictureFile){
        try {
            Commodity commodity= commodityService.pictures(pictureFile);
            commodityService.save(commodity);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }

    @RequestMapping("/commoditiesList.do")
    @ResponseBody
    public List<Commodity> commoditiesList(){
        return commodityService.list();
    }
}
