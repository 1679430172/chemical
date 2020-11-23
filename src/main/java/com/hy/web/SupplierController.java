package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
import com.hy.mapper.UserMapper;
import com.hy.service.SupplierService;
import com.hy.service.Userserves;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api
@Controller
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private Userserves userserves;

    @RequestMapping("/supplierInquire.do")
    @ResponseBody
    public ParseData supplierInquire(Integer page, Integer limit,String name){
        IPage<SupplierUsers> iPage= supplierService.iPage(page,limit,name);
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    @RequestMapping("/supplierInquires.do")
    @ResponseBody
    public ParseData supplierInquire(){
        List<SupplierUsers> iPage= supplierService.iPage();
        return new ParseData(0,"",null,iPage);
    }

    @RequestMapping("get.do")
    @ResponseBody
    public ParseData get(){
        return  new ParseData(0,"",null,supplierService.list());
    }
    @RequestMapping("/suppliers.do")
    @ResponseBody
    public Supplier suppliers(String gid){
        return supplierService.getById(gid);
    }

    @RequestMapping("/supplierAdd.do")
    @ResponseBody
    public String supplierAdd(Supplier supplier){
        System.out.println(supplier.toString());
        boolean b= supplierService.save(supplier);
        if(b == true){
            return Util.succeed;
        }else {
            return Util.fail;
        }
    }

    @RequestMapping("/users.do")
    @ResponseBody
    public List<SupplierUsers> users(){
        return supplierService.users();
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public String supplierSave(Supplier supplier){
        if (supplier.getStatus().equals("on")) {
            supplier.setStatus("0");
        }
        Boolean b = supplierService.save(supplier);
        if(b == true){
            return Util.succeed;
        }else {
            return Util.fail;
        }
    }

    @RequestMapping("/suppliers")
    @ResponseBody
    public List<Supplier> suppliers(){
        return supplierService.list();
    }

}
