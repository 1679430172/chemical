package com.hy.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Inventory;
import com.hy.service.InventoService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api
@Controller
@RequestMapping("inventory")
public class InventoryControlledr {
    @Autowired
    private InventoService InventoService;

    @RequestMapping("/inventory.do")
    @ResponseBody
    public ParseData inventory(Integer page, Integer limit){
        IPage<Inventory> iPage= InventoService.iPage(page,limit);
        System.out.println(iPage.getRecords().get(0).getCreateTime()+"------------");
        ParseData layuiDate=new ParseData();
        layuiDate.setCode(0);
        layuiDate.setCount(Integer.parseInt(Long.toString(iPage.getTotal())));
        layuiDate.setMsg("");
        layuiDate.setData(iPage.getRecords());
        return layuiDate;
    }

}
