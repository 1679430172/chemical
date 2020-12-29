package com.hy.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Gg;
import com.hy.bean.Inventory;
import com.hy.bean.Purchase;
import com.hy.service.GgService;
import com.hy.service.PurchaseService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api
@Controller
@RequestMapping("Gg")
public class GgController {

    @Autowired
    private GgService ggService;

    @RequestMapping("/Gg.do")
    @ResponseBody
    public ParseData Gg(Integer page, Integer limit){
        IPage<Gg> iPage= ggService.iPage(page,limit);
        List<Gg> list = iPage.getRecords();
        page = page == 0 ? 1 : page;
        limit = limit == 0 ? 20 : limit;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId((page - 1) * limit + (i + 1));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),list);
    }

    @RequestMapping("/Gglist")
    @ResponseBody
    public ParseData Gglist(){
        List<Gg> gg=ggService.list();
        return new ParseData(0,"",gg.size(),gg);
    }
}



