package com.hy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.bean.SupplierUsers;
import com.hy.mapper.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
    public  static final String imgFile="/huagong/data/image";
    public  static final String imgDowloadFile="/huagong/data";
    @Autowired
    private CommodityMapper commodityMapper;

    public IPage<Commoditys> CommditysList(Integer page, Integer limit,Commoditys commoditys){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        Integer userId= (Integer) session.getAttribute("userId");
        IPage<Commoditys> iPage=null;
        if(userType.equals("0") || userType.equals("2")){
            iPage= commodityMapper.CommditysList(new Page(page,limit),commoditys,null);
        }else {
            iPage = commodityMapper.CommditysList(new Page(page, limit), commoditys, String.valueOf(userId));
        }
        List<Commoditys> list=iPage.getRecords();
        for(Commoditys s:list) {
            String gid = "" + s.getSid();
            String id = "BH00000";
            id = id.substring(0, id.length() - gid.length()) + gid;
            s.setSsid(id);
        }
        iPage.setRecords(list);
         return iPage;
    }

    public Commodity byid(String sid){
        return commodityMapper.byid(sid);
    }

    public void   equals(Commodity commodity,HttpServletRequest req){
        commodityMapper.equals(commodity);
    }

    public void pictures(MultipartFile pictureFile, String sid, HttpServletRequest req) throws IOException {
        if(pictureFile != null){
            String picName = UUID.randomUUID().toString();// 设置图片名称，不能重复，可以使用uuid
            String oriName = pictureFile.getOriginalFilename();//获取文件名
            String extName = oriName.substring(oriName.lastIndexOf("."));//// 获取图片后缀
            try {
                String webAppPath= req.getServletContext().getRealPath("/");
                File uploadFile= new File(imgFile);
                if(!uploadFile.exists()){
                    uploadFile.mkdirs();
                }
                Commodity commodity=new Commodity();
                File pic = new File(uploadFile,"/"+ picName + extName);
                pictureFile.transferTo(pic);
                commodity.setImgPath("/image/"+ picName + extName);
                commodity.setImgStatus("1");
                commodity.setSid(Integer.parseInt(sid));
                commodityMapper.pictureEquals(commodity);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void file(MultipartFile pictureFile, String sid, HttpServletRequest req) throws IOException {
        if(pictureFile != null){
            String picName = UUID.randomUUID().toString();// 设置图片名称，不能重复，可以使用uuid
            String oriName = pictureFile.getOriginalFilename();//获取文件名
            String extName = oriName.substring(oriName.lastIndexOf("."));//// 获取图片后缀
            try {
                String webAppPath= req.getServletContext().getRealPath("/");
                File webAppFile = new File(webAppPath);
                File uploadFile= new File(imgFile);
                if(!uploadFile.exists()){
                    uploadFile.mkdirs();
                }
                Commodity commodity=new Commodity();
                File pic = new File(uploadFile.toString(),"/"+ picName + extName);
                pictureFile.transferTo(pic);
                commodity.setFilePath("/image/"+ picName + extName);
                commodity.setFileStatus("1");
                commodity.setSid(Integer.parseInt(sid));
                commodityMapper.paper(commodity);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void download(HttpServletRequest req,HttpServletResponse response,String sid) {
        //设置浏览器显示的内容类型为Zip  (很重要,欺骗浏览器下载的是zip文件,就不会自己打开了)
        response.setContentType("application/zip");
        Commodity commodity=commodityMapper.byid(sid);
        //设置内容作为附件下载  fileName有后缀,比如1.jpg
        String file=commodity.getImgPath();
        String[] l=file.split(".");
        response.setHeader("Content-Disposition", "attachment; filename=image."+l[1]);
        ServletOutputStream out = null;
        try {
            // 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
            String webAppPath= imgDowloadFile;
//            file=file.substring(1);
            webAppPath+=file;
            InputStream inputStream = new FileInputStream(webAppPath);//此处是为了获得输出流
            // 3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = inputStream.read(buffer);
                // 4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null)
                    out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloads(HttpServletRequest req,HttpServletResponse response,String sid) {
        //设置浏览器显示的内容类型为Zip  (很重要,欺骗浏览器下载的是zip文件,就不会自己打开了)
        response.setContentType("application/zip");
        Commodity commodity=commodityMapper.byid(sid);
        //设置内容作为附件下载  fileName有后缀,比如1.jpg
        String file=commodity.getFilePath();
        String[] l=file.split(".");
        response.setHeader("Content-Disposition", "attachment; filename=file."+l[1]);
        ServletOutputStream out = null;
        try {
            // 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
            String webAppPath= imgDowloadFile;
//            file=file.substring(1);
            webAppPath+=file;
            InputStream inputStream = new FileInputStream(webAppPath);//此处是为了获得输出流
            // 3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = inputStream.read(buffer);
                // 4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null)
                    out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer suppliers(String supplierId,String cas){
        List<Commodity> list= commodityMapper.supplierss(supplierId,cas);
        return list.size();
    }
    public Integer suppliers(String supplierId){
        return commodityMapper.suppliers(supplierId).size();
    }

}
