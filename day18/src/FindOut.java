
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FindOut implements Runnable {
    String http1;
    String information;

    public FindOut(String http1, String information) {
        this.http1=http1;
        this.information=information;
    }
    @Override
    public void run() {
        try {
            HttpURLConnection url2 = (HttpURLConnection) new URL(http1).openConnection();
            //现在我们拿到了图片的网址
            InputStream in = url2.getInputStream();
            File file = new File("E:/Spiderpictures/" + information + ".png");
            FileOutputStream out = new FileOutputStream(file);
            //FileOutputStream out = new FileOutputStream(information + ".png");
            byte[] bytes=new byte[1024*1024];
            int len=0;
            while ((len=in.read(bytes))!=-1){
                out.write(bytes,0,len);
                out.flush();
            }
            out.close();
            in.close();
            System.out.println("当前爬到"+information+"张图");
            Thread.currentThread().stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
