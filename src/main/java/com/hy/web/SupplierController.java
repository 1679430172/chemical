package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.service.SupplierService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
