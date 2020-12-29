package com.hy.web;
import com.hy.bean.upload;
import com.hy.service.uploadService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class uploadController {
    @Autowired
    private uploadService uploadService;

    @RequestMapping("/save.do")
    @ResponseBody
    public String save(@RequestParam("file") MultipartFile pictureFile, HttpServletRequest req) throws IOException {
        upload upload=uploadService.pictures(pictureFile,req);
        System.out.println(upload);
        uploadService.save(upload);
        return Util.succeed;
    }

    @RequestMapping("/select")
    @ResponseBody
    public ParseData select(){
        List<upload> iPage=  uploadService.list();
        return new ParseData(0,"",iPage.size(),iPage);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Integer update(upload upload){
     boolean bl= uploadService.updateById(upload);
        if(bl){
            return 1;
        }
        return 0;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(Integer id){
        boolean bl= uploadService.removeById(id);
        if(bl){
            return 1;
        }
        return 0;
    }

    @RequestMapping("/up")
    @ResponseBody
    public ParseData up(){
        List<upload> up=uploadService.up();
        return new ParseData(0,"",up.size(),up);
    }
    @RequestMapping("/uploads")
    @ResponseBody
    public ParseData uploads(){
        List<upload> uploads=uploadService.uploads();
        return new ParseData(0,"",uploads.size(),uploads);
    }

    @RequestMapping("/downloads")
    @ResponseBody
    public String downloads(HttpServletRequest req, HttpServletResponse response, String id){
        try {
            uploadService.downloads(req,response,id);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }
}
