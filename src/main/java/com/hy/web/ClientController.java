package com.hy.web;

import com.hy.bean.Client;
import com.hy.service.ClientService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
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
        return new ParseData(0,"",null,service.list());
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Client> list(){
        return service.list();
    }
}
