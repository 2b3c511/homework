import java.sql.*;

public class Mysql {
    public static void main(String[] args) throws SQLException {
        //1.建立客户端
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
        System.out.println(conn.isClosed());
        //2.准备sql语句
        Statement statement = conn.createStatement();//创建一个语句对象
        //3.执行sql语句
        //statement.executeUpdate("insert into student(sid,sname,birthday,sex) values(1010,'夏木','1998-08-08','女')");  //增删该
        ResultSet result = statement.executeQuery("select * from student");//执行查询
        while (result.next()){
            System.out.println(result.getInt("sid")+"=="+result.getString("sname")+"=="+result.getDate("birthday")+"=="+result.getString("sex"));
        }
//4.资源的清理
        statement.close();
        conn.close();
    }

}
//增删改
//mysql> select * from student;
//        +------+-------+------------+------+
//        | sid  | sname | birthday   | sex  |
//        +------+-------+------------+------+
//        | 1001 | 张三  | 1990-10-10 | 男   |
//        | 1002 | 李四  | 1981-10-10 | 男   |
//        | 1003 | 王五  | 1981-11-10 | 女   |
//        | 1004 | 赵六  | 1988-10-10 | 男   |
//        | 1005 | 孙七  | 1989-01-10 | 女   |
//        | 1006 | 周八  | 1990-10-10 | 男   |
//        | 1007 | 张三  | 1990-06-10 | 女   |
//        | 1010 | 夏木  | 1998-08-08 | 女   |
//        +------+-------+------------+------+
//        8 rows in set (0.05 sec)