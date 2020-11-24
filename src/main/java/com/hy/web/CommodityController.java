package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http .HttpServletResponse;
import java.util.Date;
import java.util.List;

@Api
@Controller
@RequestMapping("Commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/Commodious.do")
    @ResponseBody
    public ParseData  Commodious(Integer page, Integer limit, Commoditys commoditys){
        IPage<Commoditys> iPage=  commodityService.CommditysList(page,limit,commoditys);
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }
    @RequestMapping("/commodityById.do")
    @ResponseBody
    public Commodity  CommodityById(String sid){
        return  commodityService.byid(sid);
    }

    @RequestMapping("/pictures.do")
    @ResponseBody
    public String pictures(@RequestParam("file") MultipartFile pictureFile, String sid, HttpServletRequest req){
        try {
            commodityService.pictures(pictureFile,sid,req);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }

    @RequestMapping("/file.do")
    @ResponseBody
    public String file(@RequestParam("file") MultipartFile pictureFile, String sid, HttpServletRequest req){
        try {
            commodityService.file(pictureFile,sid,req);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }


    @RequestMapping("/save.do")
    @ResponseBody
    public String save(Commodity commodity){
        supplierService.getById(commodity.getSupplierId());
        Integer sid=commodity.getSupplierId();
        Integer supp=commodityService.suppliers(String.valueOf(sid),commodity.getCas());
        if(null==supp || supp > 0){
            return Util.defact;
        }else{
            commodity.setUpdateTime(new Date());
            boolean b= commodityService.save(commodity);
            UpdateWrapper updateWrapper=new UpdateWrapper();
            updateWrapper.set("status","1");
            updateWrapper.eq("gid",commodity.getSupplierId());
            supplierService.update(updateWrapper);
            if(b == true){
                return Util.succeed;
            }else {
                return Util.fail;
            }
        }
    }

    @RequestMapping("/commoditiesList")
    @ResponseBody
    public List<Commodity> commoditiesList(){
        return commodityService.list();
    }

    @RequestMapping("/equals.do")
    @ResponseBody
    public String  equals(Commodity commodity,HttpServletRequest req){
        System.out.println(commodity.toString());
        try {
            commodityService.equals(commodity,req);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }

    @RequestMapping("/supplierId.do")
    @ResponseBody
    public Integer Supplier(String  supplierId){
        return commodityService.suppliers(supplierId);
    }

    @RequestMapping("/download.do")
    @ResponseBody
    public String download(HttpServletRequest req,HttpServletResponse response, String sid){
        try {
            commodityService.download(req,response,sid);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }

    @RequestMapping("/downloads.do")
    @ResponseBody
    public String downloads(HttpServletRequest req,HttpServletResponse response, String sid){
        try {
            commodityService.downloads(req,response,sid);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }
}
