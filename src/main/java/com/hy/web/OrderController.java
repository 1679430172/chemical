package com.hy.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.Inventory;
import com.hy.bean.Order;
import com.hy.service.OrderService;
import com.hy.util.ImgUtils;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Api
@Controller
@RequestMapping("Order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/")
    @ResponseBody
    public ParseData select(Integer page, Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        return service.selectList(page1);
    }

    @PostMapping("/")
    @ResponseBody
    public Integer add(Order order) throws Exception {
        return service.add(order);
    }

    @RequestMapping("/toAdd")
    @ResponseBody
    public ModelAndView toAdd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addOrder.html");
        return modelAndView;
    }

    @RequestMapping("/selectByStatus")
    @ResponseBody
    public ParseData selectByStatus(Integer page, Integer limit){
        Page page1=new Page(page,limit);
        return service.selectListByStatus(page1);
    }

    @PutMapping("/")
    @ResponseBody
    public Integer updStatus(Integer did){
        return service.updStatus(did);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Integer del(Integer did){
        boolean f;
        f = service.removeById(did);
        if(f){
            return 1;
        }
        return 0;
    }

    @GetMapping("/time")
    @ResponseBody
    public ParseData select(String stadate,String enddate,String name,Integer page, Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        return service.selectListTime(stadate,enddate,name,page1);
    }

    @PostMapping("/cz")
    @ResponseBody
    public List<Commodity> cz(String cas,String name){
        List<Commodity> list=service.cz(cas,name);
        for(Commodity s:list) {
            String gid = "" + s.getSid();
            String id = "BH00000";
            id = id.substring(0, id.length() - gid.length()) + gid;
            s.setCommodityInfo(id);
            System.out.println(s.getCommodityInfo());
        }
        return list;
    }

    @RequestMapping("/toUpd")
    @ResponseBody
    public ModelAndView toUpd(Integer did){
        ModelAndView modelAndView=new ModelAndView();
        Order order=service.getById(did);
        System.out.println(order);
        modelAndView.addObject("order",order);
        modelAndView.setViewName("updOrder.html");
        return modelAndView;
    }

    @PostMapping("/upd")
    @ResponseBody
    public Integer upd(Order order,String did){
        boolean f;
        f = service.updateById(order);
        if(f){
            return 1;
        }
        return 0;
    }

    @GetMapping("/invento")
    @ResponseBody
    public Inventory getInvento(Integer kid){
        return service.getinvento(kid);
    }

    @GetMapping("/userType")
    @ResponseBody
    public List userType(HttpServletRequest request) throws Exception {
        List arr=new ArrayList();
        arr.add( Integer.valueOf(request.getSession().getAttribute("userType").toString()));
        arr.add( Integer.valueOf(request.getSession().getAttribute("userId").toString()));
        return arr;
    }
    @ResponseBody
    @RequestMapping("/imgUpload")
    public void imgUpload(HttpServletResponse response,String name,String amount,String companyName,String phone){
        try {
            response.setContentType("image/png");
            //获取批号（当前日期减10天，格式：20201214）
            Calendar c=Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH,-10);
            String number=new SimpleDateFormat("yyyyMMdd").format(c.getTime());
            //基础信息
            BufferedImage image = ImgUtils.imgSave(name,amount+"千克",number,"广州远达新材料有限公司","18620540041");
            //以png格式向客户端发送
            response.setHeader("Content-Disposition", "attachment; filename=image.png");
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(image, "PNG",os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
