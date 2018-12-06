

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class PictureSpider {
    public static void main(String[] args) throws IOException {
//        URL 返回的是html网页
//		<img 任意  src="地址"
//        List<> list = 记录所有地址

        //阻塞IO,线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        //https://tieba.baidu.com/p/2256306796?red_tag=1781367364
        HttpURLConnection url = (HttpURLConnection) new URL("https://tieba.baidu.com/p/2256306796?red_tag=1781367364").openConnection();
        //获取输入流对象
        InputStream in = url.getInputStream();
        //获取一个文件输出流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        StringBuffer stringBuffer=new StringBuffer();
        int len=0;
        String string;
        while ((string=bufferedReader.readLine())!=null) {
            stringBuffer.append(new String(string));//把字符串存到StringBuffer
        }
        in.close();
        url.disconnect();
        //传递参数现在我爬的所有信息在StringBuffer中开始正则匹配找出的所有图片
        int point=0;
        String startstring="<img ";
        int count=1;
        while ((point=stringBuffer.indexOf(startstring,point))!=-1){
            //先找头索引start
            // 找到头之后找尾索引end
//             <img class="card_head_img" src="https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg? wapp&amp;quality=80&amp;size=b150_150&amp;subsize=20480&amp;cut_x=0&amp;cut_w=0&amp;cut_y=0&amp;cut_h= " +
//                    "0&amp;sec=1369815402&amp;" + srctrace&amp;di=c55dd5519b472cb9f5d9fece88fc2bf0&amp;wh_rate=null&amp;
//                    src=htt p%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Fd31b0ef41bd5ad6e4ae168d987cb39dbb6fd3cec.jpg" />
            String endstring=">";
            int end=stringBuffer.indexOf(endstring,point);
            //此时找到一个img
            String address=stringBuffer.substring(point,end);
            int httpstart=address.indexOf("https:");
            if(httpstart!=-1){
                String ends="\"";//引号结束
                int httpend=address.indexOf(ends,httpstart);
                String httpaddress=address.substring(httpstart,httpend);
                threadPoolExecutor.submit(new FindOut(httpaddress,String.valueOf(count++)));
            }
            point++;
        }
        threadPoolExecutor.shutdown();
    }
}