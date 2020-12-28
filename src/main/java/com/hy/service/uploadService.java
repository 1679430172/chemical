package com.hy.service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.upload;
import com.hy.mapper.uploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class uploadService extends ServiceImpl<uploadMapper, upload> {

    public  static final String imgFile="/huagong/data/image";
    @Autowired
    private uploadMapper uploadMapper;

    public upload pictures(MultipartFile pictureFile, HttpServletRequest req) throws IOException {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userType= (String) session.getAttribute("userType");
        String userName=(String) session.getAttribute("userName");
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
                upload.setName(userName);
                upload.setType(userType);
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
}
