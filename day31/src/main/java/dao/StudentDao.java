package dao;

import domain.Student;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 数据访问对象
public class StudentDao {
    // 查询所有
    public List<Student> findAll() {
        try (Connection conn = JdbcUtils.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select * from student")) {
                ResultSet rs = stmt.executeQuery();
                List<Student> list = new ArrayList<>();
                while (rs.next()) {
                    Student stu = new Student();
                    stu.setSid(rs.getInt("sid"));
                    stu.setSname(rs.getString("sname"));
                    stu.setBirthday(rs.getDate("birthday"));
                    stu.setSex(rs.getString("sex"));
                    list.add(stu);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 添加学生
    public void insert(Student student) {

        String sql = "insert into user(sid,sname,birthday,sex) values(?,?,?)";
        try (Connection conn = JdbcUtils.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setString(1, student.getSname());
                psmt.setDate(2, new java.sql.Date(student.getBirthday().getTime()));
                psmt.setString(3, student.getSname());
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据 id 查询
    public Student findById(int sid) {
        String sql = "select * from student where sid=" + sid;
        try (Connection conn = JdbcUtils.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                List<Object> list = new ArrayList<>();
                ResultSet result = psmt.executeQuery();
                while (result.next()) {
                    Student stu = new Student();
                    stu.setSid(result.getInt("sid"));
                    stu.setSname(result.getString("sname"));
                    stu.setBirthday(result.getDate("birthday"));
                    stu.setSex(result.getString("sex"));
                    list.add(stu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 修改学生
    public void update(Student student) {
        String sql = "update student set " + "sname=" + student.getSname() + ",birhtday=" + student.getBirthday() + ",sex=" + student.getSex() + " where sid=" + student.getSid();
        try (Connection conn = JdbcUtils.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 删除学生
    public void delete(int sid) {
        String sql = "delete from student where sid=" + sid;
        try (Connection conn = JdbcUtils.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
