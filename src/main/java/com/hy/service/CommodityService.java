package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.SupplierUsers;
import com.hy.bean.User;
import com.hy.mapper.CommodityMapper;
import com.hy.mapper.SupplierMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
    @Autowired
    private CommodityMapper commodityMapper;

    public IPage<Commoditys> CommditysList(Integer page, Integer limit,Commoditys commoditys){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        Integer userId= (Integer) session.getAttribute("userId");
        IPage<Commoditys> iPage=null;

        /*if(userType.equals("0") || userType.equals("2")){
            commodityMapper.CommditysList(new Page(page,limit),commoditys,null);
            return iPage;
        }else if(userType == "1"){
            commodityMapper.CommditysList(new Page(page,limit),commoditys,String.valueOf(userId));
            return iPage;
        }*/
        return commodityMapper.CommditysList(new Page(page,limit),commoditys,String.valueOf(userId));
    }

    public Commodity byid(String sid){
        return commodityMapper.byid(sid);
    }

    public void   equals(Commodity commodity){
        commodityMapper.equals(commodity);
    }



    public void pictures(MultipartFile pictureFile,String sid) throws IOException {

        if(pictureFile != null){
            String picName = UUID.randomUUID().toString();// 设置图片名称，不能重复，可以使用uuid
            String oriName = pictureFile.getOriginalFilename();//获取文件名
            String extName = oriName.substring(oriName.lastIndexOf("."));//// 获取图片后缀
            Commodity commodity=new Commodity();
            try {
                File file=new File("/assets/" + picName + extName);
                pictureFile.transferTo(file);
                System.out.println(file.toString()+"-----------------");
                commodity.setImgPath(file.toString());
                commodity.setImgStatus("1");
                commodity.setSid(Integer.parseInt(sid));
                System.out.println(commodity.toString()+"+++++++++++++++++++");
                commodityMapper.pictureEquals(commodity);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                //model.addAttribute("msg", "上传失败");
               // return "/error.jsp";
            } catch (IOException e) {
                e.printStackTrace();
                //model.addAttribute("msg", "上传失败");
               // return "/error.jsp";
            }
        }
    }

    public Integer userIdBySid(String sid){
        //return commodityMapper.byid(sid);
        return 1;
    }
}
