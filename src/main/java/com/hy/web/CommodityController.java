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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Api
@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @RequestMapping("/Commoditys.do")
    @ResponseBody
    public ParseData  Commoditys(Integer page, Integer limit, Commoditys commoditys){
        IPage<Commoditys> iPage=  commodityService.CommditysList(page,limit,commoditys);
        ParseData layuiDate=new ParseData();
        layuiDate.setCode(0);
        layuiDate.setCount(Integer.parseInt(Long.toString(iPage.getTotal())));
        layuiDate.setMsg("");
        layuiDate.setData(iPage.getRecords());
        return layuiDate;
    }

    @RequestMapping("/pictures.do")
    @ResponseBody
    public String pictures(@RequestParam("file") MultipartFile pictureFile){
        try {
            InputStream inputStream= pictureFile.getInputStream();
            List<Commodity> empDepts= commodityService.pictures(inputStream);

            commodityService.save((Commodity) empDepts);
        } catch (IOException e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }
}
