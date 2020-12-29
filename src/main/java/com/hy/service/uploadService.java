package com.hy.service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Commodity;
import com.hy.bean.upload;
import com.hy.mapper.uploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class uploadService extends ServiceImpl<uploadMapper, upload> {
    public  static final String imgDowloadFile="/huagong/data";
    public  static final String imgFile="/huagong/data/image";
    @Autowired
    private uploadMapper uploadMapper;

    public upload pictures(MultipartFile pictureFile, HttpServletRequest req) throws IOException {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Integer userId=(Integer) session.getAttribute("userId");
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
                File pic = new File(uploadFile,"/"+ picName + extName);
                pictureFile.transferTo(pic);
                upload upload=new upload();
                upload.setFile(oriName);
                upload.setUid(userId);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                upload.setUploadDate(simpleDateFormat.format(new Date()));
                return upload;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public List<upload> uploads(){
        List<upload> list=uploadMapper.upload();
        for(int i=0; i<list.size();i++){
            System.out.println(list.get(i).getType()+"=======================");
            if(list.get(i).getType().equals("1") ){
                list.remove(i);
            }
        }
        return list;
    }

    public List<upload> up(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Integer userId= (Integer) session.getAttribute("userId");
        List<upload> list=uploadMapper.uploads(userId);
        return list;
    }

    public void downloads(HttpServletRequest req, HttpServletResponse response, String sid) {
        //设置浏览器显示的内容类型为Zip  (很重要,欺骗浏览器下载的是zip文件,就不会自己打开了)
        response.setContentType("application/zip");
        upload upload=uploadMapper.selectById(sid);
        //设置内容作为附件下载  fileName有后缀,比如1.jpg
        String file=upload.getDescription();
        response.setHeader("Content-Disposition", "attachment; filename=file.");
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
}
