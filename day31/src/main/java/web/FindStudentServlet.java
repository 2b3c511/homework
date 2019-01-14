package web;

import dao.StudentDao;
import domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findStudent")
public class FindStudentServlet extends HttpServlet {

    StudentDao studentDao = new StudentDao(); // 只有一个对象

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询student所有信息
        List<Student> list = studentDao.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("findStudent.jsp").forward(req, resp);

    }
}
