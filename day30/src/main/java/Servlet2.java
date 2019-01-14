package MyServlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/Servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建图片对象
        BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
        //创建画布对象
        Graphics2D g = image.createGraphics();
        //填充
        g.setColor(Color.cyan);//选颜色
        g.fillRect(0,0,200,100);//选择坐标轴和宽高
        //生成随机数
        String string="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPGRSTUVWXYZ";
        StringBuffer buffer = rand(string);
        //写入文字
        g.setColor(Color.BLACK);
        g.setFont(new Font("黑体",Font.ITALIC,64));
        g.drawString(buffer.toString(),0,64);
        g.drawLine(0,0,200,100);
        g.drawLine(100,0,0,200);
        //把图片内容输出相应
        resp.setContentType("imag/png");//设置文件格式
        ImageIO.write(image,"png",resp.getOutputStream());


    }

    private StringBuffer rand(String string) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<4;i++){
            buffer=buffer.append(string.charAt(random.nextInt(string.length())));
        }
        return buffer;
    }
}
