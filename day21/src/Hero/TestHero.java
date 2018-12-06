package Hero;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Enum.valueOf;
import static javafx.scene.input.KeyCode.T;

public class TestHero {

    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Paths.get("heroes.txt"), Charset.forName("utf-8"));
        // \t  split("\t")
        //lines.forEach(str-> System.out.println(str));

        // 1. 找到武将中武力前三的hero对象, 提示流也可以排序                                                          √
        // 2. 按出生地分组                                                                                       √
        // 3. 找出寿命前三的武将                                                                                    √
        // 4. 女性寿命最高的                                                                                   √
        // 5. 找出武力排名前三  100, 99, 97 97 ==> 4个人 吕布", "张飞", "关羽", "马超                           √
        // 6. 按各个年龄段分组，0~20,21~40,41~60,60以上                                                            √
        // 7. 按照武力段分组： >=90 80~89 70~79 <70
        // 8. 按照出生地分组，统计各组人数                                                                            √
        int[] power=new int[1024];
        Vector<Hero> list1 = new Vector<>();//用来存放武力值前三的hero对象
        Vector<Hero> list = new Vector<>();//原来的所有hero集合
        Vector<Hero> list2 = new Vector<>();//原来的所有hero集合
        lines.forEach(str -> {
            String[] split = str.split("\t");
            Hero hero = new Hero(Integer.parseInt(split[0]), split[1], split[2], split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]));
            list.add(hero);
        });
        Stream<Hero> stream = list.stream().sorted((o1, o2) -> o2.getPower() - o1.getPower());//按照武力值排序
        System.out.println("----------------------------------------------武力值排序输出----------------------------------------------------");
        long count = list.stream().count();
        //System.out.println(count);
        stream.limit(count).forEach(hero -> {
            //System.out.println(hero);
            list2.add(hero);
        });
        System.out.println("=================================list遍历========================================================");
        list2.forEach(hero -> System.out.println(hero));
        System.out.println("========================list2武力值前三的hero遍历==============================================");
        list1.add(list2.get(0));
        list1.add(list2.get(1));
        list1.add(list2.get(2));
        for (int i = 3; i < list.size();i++) {
            if(list2.get(i).getPower()==list1.get(2).getPower()){
                list1.add(list2.get(i));
            }
        }
        list1.forEach(hero -> System.out.println(hero));
        System.out.println("==========================================按出生地分组==&&统计各地人数=====================================================================");
        //Stream<Hero> stream2 = list.stream();
        Map<String, List<Hero>> map = new HashMap<>();//出生地--hero对象集合
        list.stream().forEach(hero -> {
            if(!map.containsKey(hero.getLoc())){//map里没有这个地址
                List<Hero> herolist = new ArrayList<Hero>();
                herolist.add(hero);
                map.put(hero.getLoc(),herolist);
            }else{
                map.get(hero.getLoc()).add(hero);//找出地址对应的list 集合将hero加入集合中
            }
        });
        map.forEach((key,value)->{
            System.out.println(key+"=="+value.size()+"人");
            value.forEach(hero -> System.out.println(hero));
        });
        System.out.println("=================================按照武力值分组=按照武力段分组： >=90 80~89 70~79 <70=======================================");
        HashMap<String, List<Hero>> map2 = new HashMap<>();
        map2.put(">=90",new Vector<Hero>());
        map2.put(">=80",new Vector<Hero>());
        map2.put(">=70",new Vector<Hero>());
        map2.put("<70",new Vector<Hero>());
        list.stream().forEach(hero -> {
            if(hero.getPower()>=90){
                map2.get(">=90").add(hero);
            }else if(hero.getPower()>=80){
                map2.get(">=80").add(hero);
            }else if(hero.getPower()>=70){
                map2.get(">=70").add(hero);
            }else{
                map2.get("<70").add(hero);
            }
        });
        map2.forEach((key,value)->{
            System.out.println(key);
            value.forEach(hero -> System.out.println(hero));
        });
        System.out.println("=======================寿命前三的武将===============================================");
        //按照年龄的大小排序
        Vector<Hero> list4 = new Vector<>();//按照寿命排序
        Vector<Hero> list5 = new Vector<>();//寿命前三的武将对象
        Stream<Hero> stream1 = list.stream().sorted((hero1, hero2) -> hero2.getage() - hero1.getage());
        stream1.limit(count).forEach(hero -> list4.add(hero));
        //list4.forEach(hero -> System.out.println(hero+"=="+hero.getage()));//遍历所有
        list5.add(list4.get(0));
        list5.add(list4.get(1));
        list5.add(list4.get(2));
        for (int i = 3; i < list4.size(); i++) {
            if(list4.get(i).getage()==list5.get(2).getage()){
                list5.add(list4.get(i));
            }
        }
        list5.forEach(hero -> System.out.println(hero+"=age="+hero.getage()));
        System.out.println("=================按各个年龄段分组，0~20,21~40,41~60,60以上==============================");
        HashMap<String ,List<Hero>> map1 = new HashMap<>();
        map1.put("0~20", new Vector<Hero>());
        map1.put("21~40", new Vector<Hero>());
        map1.put("41~60", new Vector<Hero>());
        map1.put("60以上", new Vector<Hero>());
        list.stream().forEach(hero -> {
            if(hero.getage()<=20){
                map1.get("0~20").add(hero);
            }else if(hero.getage()<=40){
                map1.get("21~40").add(hero);
            }else if(hero.getage()<=60){
                map1.get("41~60").add(hero);
            }else {
                map1.get("60以上").add(hero);
            }
        });
        map1.forEach((key,value)->{
            System.out.println(key+"===");
            value.forEach(hero -> System.out.println(hero+"===="+hero.getage()));
        });
        System.out.println("============================女性寿命最高的==========================================");
        Vector<Hero> famale = new Vector<>();//所有女性集合
        Vector<Hero> list6 = new Vector<>();
        list.stream().forEach(hero -> {
            if(hero.getSex().equals("女")){
                //System.out.println(hero+"=age="+hero.getage());
                famale.add(hero);//所有女性的集合
            }
        });
        //System.out.println(famale.size());
        Stream<Hero> stream2 = famale.stream().sorted((hero1, hero2) -> hero2.getage() - hero1.getage());//女将按寿命排序
        //System.out.println("========================女将按照寿命排序输出==========================");
        stream2.limit(count).forEach(hero -> {
            //System.out.println(hero+"=age="+hero.getage());
            list6.add(hero);
        });
        System.out.println("寿命最高的女将：");
        System.out.println(list6.get(0)+"==age=="+list6.get(0).getage());
    }
}
