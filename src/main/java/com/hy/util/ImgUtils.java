package com.hy.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yaojia
 * @version V1.0
 * @Package com.hy.util
 * @Description: TODO
 * @date 2020/12/9 14:22
 */
public class ImgUtils {
    /**
     *  生成标签图片
     * @param name          产品名称
     * @param amount        数量
     * @param number        批号  （当前日期减10天 ，格式：20201214）
     * @param companyName  公司名称
     * @param phone         联系电话
     * @return
     * @throws IOException
     */
    public static BufferedImage imgSave(String name,String amount,String number,String companyName,String phone) throws IOException {
        int width = 500; // 图片宽
        int height = 500;// 图片高
        String titleStr = "产品标签";
        // 得到图片缓冲区
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150

        // 得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setColor(Color.WHITE); // 设置背景颜色
        g2.fillRect(0, 0, width, height);// 填充整张图片(其实就是设置背景颜色)
        g2.setColor(Color.black);// 设置字体颜色
        g2.setStroke(new BasicStroke(2.0f)); // 边框加粗
        g2.drawRect(1, 1, width - 2, height - 2); // 画边框就是黑边框

        g2.drawLine(0, 80, 820, 80); // 从上到下第二个横线(标题下面横线)
        g2.setStroke(new BasicStroke(0.0f)); // 边框不需要加粗
        g2.drawLine(0, 154, 820, 154); // 从上到下第三个横线(账单周期下面横线)
        g2.drawLine(0, 228, 820, 228); // 从上到下第四个横线(账单天数下面横线)
        g2.drawLine(0, 302, 820, 302); // 从上到下第5个横线(泵额定功率下面横线)
        g2.drawLine(0, 376, 820, 376); // 从上到下第6个横线(泵变频节能运行用电量下面横线)
//        g2.drawLine(0, 451, 820, 451); // 从上到下第7个横线(电价下面横线)
//        g2.drawLine(0, 525, 820, 525); // 从上到下第8个横线(平均节能率下面横线)

        g2.drawLine(180, 80, 180, 600); // 从左到右第二个竖线
//        g2.drawLine(390, 154, 390, 451); // 从左到右第三个竖线
//        g2.drawLine(574, 154, 574, 451); // 从左到右第四个竖线

        // 设置标题的字体,字号,大小
        Font titleFont = new Font("微软雅黑", Font.BOLD, 30);
        g2.setFont(titleFont);
        String markNameStr = titleStr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿
        // 计算文字长度,计算居中的X点坐标
        FontMetrics fm = g2.getFontMetrics(titleFont);
        int titleWidth = fm.stringWidth(markNameStr);
        int titleWidthX = (width - titleWidth) / 2;// 感觉不居中,向左移动35个单位
        g2.drawString(markNameStr, titleWidthX, 45);

        // 产品名称
        g2.setFont(new Font("宋体", Font.BOLD, 20));
        g2.drawString("产品名称", 33, 125);
        // 产品名称的值
        g2.drawString(name, 230, 125);

        // 数量
        g2.drawString("数量", 33, 200);
        // 数量的值
        g2.drawString(amount, 230, 200);

        // 批号
        g2.drawString("批号", 33, 274);
        // 批号
        g2.drawString(number, 230, 274);

        // 公司名称
        g2.drawString("公司名称", 33, 338);

        // // 公司名称的值
        g2.drawString(companyName, 230, 345);

        // 联系电话
        g2.drawString("联系电话", 33, 423);
        //  联系电话的值
        g2.drawString(phone, 230, 423);

        g2.dispose(); // 释放对象
//        ImageIO.write(bi, "JPEG", new FileOutputStream("C:\\Users\\yaojia\\Desktop\\a.jpg"));// 保存图片 JPEG表示保存格式
        return bi;
    }

    public static void main(String[] args) throws IOException {
        imgSave("李四","5kg","20201210","湖北","123");

    }
}
