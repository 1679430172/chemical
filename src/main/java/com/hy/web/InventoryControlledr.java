package com.hy.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Inventory;
import com.hy.bean.Order;
import com.hy.service.InventoService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @GetMapping("/")
    @ResponseBody
    public List<Inventory> selectListByCas(String cas){
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cas",cas);
        return InventoService.list(queryWrapper);
    }

    @PostMapping("/addinventory.do")
    @ResponseBody
    public Integer add(Inventory inventory) throws Exception {
        System.out.println(inventory);
        return 1;
    }
}
