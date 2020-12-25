package com.hy.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Inventory;
import com.hy.mapper.InventoryMapper;
import com.hy.service.InventoService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Api
@Controller
@RequestMapping("inventory")
public class InventoryControlledr {
    @Autowired
    private InventoService InventoService;
    @Autowired
    private InventoryMapper inventoryMapper;

    @RequestMapping("/inventory.do")
    @ResponseBody
    public ParseData inventory(Integer page, Integer limit){
        IPage<Inventory> iPage= InventoService.iPage(page,limit);
        List<Inventory> list = iPage.getRecords();
        page = page == 0 ? 1 : page;
        limit = limit == 0 ? 20 : limit;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setXid((page - 1) * limit + (i + 1));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),list);
    }

    @RequestMapping("/select1")
    @ResponseBody
    public ParseData select1(Integer page, Integer limit){
        IPage<Inventory> iPage=InventoService.querylist1(page,limit);
        List<Inventory> list = iPage.getRecords();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setXid((page - 1) * limit + (i + 1));
        }
        return  new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),list);

    }

    @PostMapping("/addinventory.do")
    @ResponseBody
    public String add(Inventory inventory) throws Exception {
        return InventoService.add(inventory);
    }

    @PostMapping("/updateInventory.do")
    @ResponseBody
    public String updateInventory(Inventory inventory) throws Exception {
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("kid",inventory.getKid());
        Inventory inventory1=InventoService.getOne(queryWrapper);
        Inventory b2=null;
        Inventory b1=null;
        if(!inventory1.getCas().equals(inventory.getCas())){
            b2=inventoryMapper.selectcas(inventory.getCas());
        }
        if(!inventory1.getNumber().equals(inventory.getNumber())){
            b1=inventoryMapper.selectnumber(inventory.getNumber());;
        }
        if(b1==null &&b2==null){
            UpdateWrapper<Inventory> updateWrapper=new UpdateWrapper<>();
            updateWrapper.set("number",inventory.getNumber());
            updateWrapper.set("name",inventory.getName());
            updateWrapper.set("cas",inventory.getCas());
            updateWrapper.set("cf",inventory.getCf());
            updateWrapper.set("remark",inventory.getRemark());
            updateWrapper.set("amount",inventory.getAmount());
            updateWrapper.eq("kid",inventory.getKid());
           InventoService.update(updateWrapper);
            return  Util.sueess;
        }
        return Util.defact;

    }

    @GetMapping("/")
    @ResponseBody
    public List<Inventory> selectListByCas(String cas){
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cas",cas);
        return InventoService.list(queryWrapper);
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

        List<Inventory> list = iPage.getRecords();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setXid((page - 1) * limit + (i + 1));
        }

        return new ParseData(0, "", Integer.parseInt(Long.toString(iPage.getTotal())), list);

    }


    @RequestMapping("/delete")
    @ResponseBody
    public String detelep(String number){
        return InventoService.detelep(number);
    }

    @RequestMapping("/toInventory")
    @ResponseBody
    public ModelAndView toInventory(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("inventory.html");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping ("/autoUpdate.do" )
    public Integer autoUpdate(Inventory inventory){
        return InventoService.autoUpdateBySid(inventory);
    }

    @ResponseBody
    @RequestMapping ("/UpdateRe.do" )
    public Integer UpdateRe(Inventory inventory){
        return InventoService.UpdateRe(inventory);
    }

    @ResponseBody
    @RequestMapping ("/jUpdate.do" )
    public Integer jUpdate(Inventory inventory){
        return InventoService.autoUpdateBykid(inventory);
    }

}
