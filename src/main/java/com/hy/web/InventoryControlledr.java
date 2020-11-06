package com.hy.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Inventory;
import com.hy.bean.Order;
import com.hy.service.InventoService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    @PostMapping("/addinventory.do")
    @ResponseBody
    public Integer add(Inventory inventory) throws Exception {
        System.out.println(inventory);
        return 1;
    }

    @RequestMapping("/queryinventory.do")
    @ResponseBody
    public ParseData queryinventory(Integer page, Integer limit,Inventory inventory) {
        System.out.println("inventory:" + inventory);
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
        if(inventory.getName() != null&&!"".equals(inventory.getName())){
            queryWrapper.like("name",inventory.getName());
        }
        if (inventory.getCas() != null&&!"".equals(inventory.getCas())){
            queryWrapper.eq("cas",inventory.getCas());
        }
        if (inventory.getNumber() != null&&!"".equals(inventory.getNumber())){
            queryWrapper.eq("number",inventory.getNumber());
        }
        IPage<Inventory> iPage=InventoService.page(InventoService.iPage(page,limit),queryWrapper);

        return new ParseData(0, "", Integer.parseInt(Long.toString(iPage.getTotal())), iPage.getRecords());

    }
}
