package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
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

import javax.annotation.PostConstruct;
import java.util.List;

@Api
@Controller
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/supplierInquire.do")
    @ResponseBody
    public ParseData supplierInquire(Integer page, Integer limit){
        IPage<SupplierUsers> iPage= supplierService.iPage(page,limit);
        System.out.println(iPage.getRecords().get(0).getUserName()+"------------");
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    @RequestMapping("/supplierInquires.do")
    @ResponseBody
    public ParseData supplierInquire(){
        List<SupplierUsers> iPage= supplierService.iPage();
        return new ParseData(0,"",null,null);
    }

    @RequestMapping("/users.do")
    @ResponseBody
    public List<User> users(){
        return supplierService.users();
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public String supplierSave(Supplier supplier){
        System.out.println(supplier.toString()+"-------");
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
}
