package Treasure;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Treasure {
    @SuppressWarnings("ReflectionForUnavailableAnnotation")
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // E:\9.22实训班共享\笔记资料
        // 类加载器, 作用：加载一个不在classpath下的类
        ClassLoader cl = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    FileInputStream in  = new FileInputStream("D:\\IDEAcode\\ReflectStream\\Treasure.class");
                    byte[] bytes = new byte[1024*8];
                    int len = in.read(bytes);

                    // 调用父类的方法根据字节数组加载类
                    return defineClass(name, bytes, 0, len);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        //有宝藏的方法有标记    @Override 过时
        //类型未知
        Class<?> clazz = cl.loadClass("com.westos.Treasure"); // 根据类名加载类, 得到类对象
        //Method[] methods1 = clazz.getDeclaredMethods();
        Constructor methods = clazz.getDeclaredConstructor();
        //Resource[] resources=new Resource[methods.length];
        //int i=0;
        //for (Method m:methods
        //     ) {
        methods.setAccessible(true);
        Object o = methods.newInstance();
        Method[] methods2 = clazz.getMethods();
        //    resources[i]= m.getAnnotation(Resource.class);
        //    System.out.println(resources[i]);
        //    i++;
        //}
        //System.out.println("============================"+methods.length);
        //for (int j=0;j<methods1.length;j++) {
//            if(resources[j]!=null){
//                System.out.println(resources[j]);
//                methods[j].invoke(clazz);
//            }

        // }
        for (Method m:methods2
                ) {
            Resource annotation = m.getAnnotation(Resource.class);
            if(annotation!=null){
                m.invoke(o);
            }
        }
    }
    //[Ljava.lang.annotation.Annotation;@66d3c617
    //            if(method.getAnnotations().toString().equals("[Ljava.lang.annotation.Annotation;@66d3c617")){
    //                System.out.println(method);
    //     //           method.invoke(clazz);
    //            }
        /*
        public void com.westos.Treasure.me247e3d53e724001a4e828add5698d3c()
public void com.westos.Treasure.m58bc2e8a640640c2b29559a2c377ab13()
public void com.westos.Treasure.mc21085e018bc48f586b0365a2778263d()
         */
}