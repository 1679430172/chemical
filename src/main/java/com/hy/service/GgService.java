package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Gg;
import com.hy.mapper.GgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class GgService extends ServiceImpl<GgMapper, Gg> {
    @Autowired
    private GgMapper ggMapper;
    public IPage<Gg> iPage(Integer page, Integer limit){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String type= (String) session.getAttribute("userType");
        IPage<Gg> iPage= ggMapper.quergg(new Page(page,limit));
        List<Gg> list=iPage.getRecords();
        for(Gg s:list){
            s.setType(type);
        }
        return iPage;
    }


}
