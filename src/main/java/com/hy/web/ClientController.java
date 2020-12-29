package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.bean.Client;
import com.hy.service.ClientService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api
@Controller
@RequestMapping("Client")
public class ClientController {
    @Resource
    private ClientService service;

    @PostMapping("/")
    @ResponseBody
    public Integer add(Client client){
        boolean f=service.save(client);
        if(f){
            return 1;
        }
        return 0;
    }

    @GetMapping("/")
    @ResponseBody
    public ParseData get(){
        List<Client> list=service.list();
        for(Client s:list){
            String did=""+s.getCid();
            String id="KH00000";
            id=id.substring(0,id.length()-did.length())+did;
            s.setCids(id);
        }
        return new ParseData(0,"",null,list);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Client> list(){
        return service.list();
    }

    @DeleteMapping("/")
    @ResponseBody
    public Integer del(Integer cid){
        boolean f=service.removeById(cid);
        if(f){
            return 1;
        }
        return 0;
    }

    @PostMapping("/upd")
    @ResponseBody
    public Integer upd(Client client){
        boolean f=service.updateById(client);
        if(f){
            return 1;
        }
        return 0;
    }

    @GetMapping("/get")
    @ResponseBody
    public Client get(Integer cid){
        return service.getById(cid);
    }

    @GetMapping("/name")
    @ResponseBody
    public ParseData name(String name){
        System.out.println(123);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("name",name);
        List<Client> list=service.list(queryWrapper);
        for(Client s:list){
            String did=""+s.getCid();
            String id="KH00000";
            id=id.substring(0,id.length()-did.length())+did;
            s.setCids(id);
        }
        return new ParseData(0,"",null,list);
    }
}
