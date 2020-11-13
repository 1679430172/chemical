package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Login;
import com.hy.bean.User;
import com.hy.service.Userserves;
import com.hy.util.ParseData;
import com.hy.util.Util;
import com.mysql.cj.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/login")
public class Logincro {

    @Autowired
    private Userserves userserves;

    /**
     * 登陆页面服务端
     * @param user
     * @return
     */
    @RequestMapping("login.do")
    @ResponseBody
    public String  Login(@RequestBody User user){
        return userserves.selecet(user);
    }

    /**
     * 业务员服务端
     * @return
     */
    @RequestMapping("/yewuyuan")
    @ResponseBody
    public ParseData Yewuyaun(Integer page ,Integer limit){
        return userserves.selectlist(page, limit);
    }

    /**
     * 获取session（权限管理）
     * @param request
     * @return
     */
    @RequestMapping("/session")
    @ResponseBody
    public String getsession(HttpServletRequest request){
        return userserves.getsession(request);
    }

    /**
     * 通过id查询当前业务员服務端
     * @param id
     * @return
     */
    @RequestMapping("/getbyid")
    @ResponseBody
    public User getbyid(@Param("id") String id){
        return userserves.getbyid(id);
    }

    /**
     * 修改业务员信息服务端
     * @param user
     * @return
     */
    @RequestMapping("/updated")
    @ResponseBody
    public String update( User user){
        return  userserves.update(user);
    }

    /**
     * 查询权限类型
     * @return
     */
    @RequestMapping("/selecttype")
    @ResponseBody
    public List<User> selecttype(){
        return  userserves.selecttype();
    }

    /**
     * 插入业务员信息
     * @param user
     * @return
     */
    @RequestMapping("/insertpp")
    @ResponseBody
    public String ttt(User user){
        return userserves.tt(user);
    }

    /**
     * 删除业务员信息服务端
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String detele(Integer id){
        return userserves.detele(id);
    }


    @RequestMapping("/getSessionUserId")
    @ResponseBody
    public String getSessionUserId(){
        return userserves.getSessionUserId();
    }

    private char[] codeSequence = { 'A', '1','B', 'C', '2','D','3', 'E','4', 'F', '5','G','6', 'H', '7','I', '8','J',
            'K',   '9' ,'L', '1','M',  '2','N',  'P', '3', 'Q', '4', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    @RequestMapping("/code")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        int width = 63;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("authCode", strCode.toLowerCase());
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    public  Color getColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

}
