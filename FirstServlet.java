import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import Students.Admin;

@WebServlet(name = "FirstServlet", value = "/FirstServlet")
public class FirstServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //out.println("<%@ page contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8 %>");
        String pointer;
        pointer= request.getParameter("pointer");
        System.out.println(pointer);
        if(pointer.equals("Вход"))
        {
            String entered_login;
            String entered_password;
            entered_login= request.getParameter("login");
            entered_password = request.getParameter("password");
            Admin admin = new Admin();
            System.out.println(entered_login);
            System.out.println(entered_password);
            if(admin.LoginPasswordCheck(entered_login,entered_password).equals("ok"))
            {
                out.println("<h1>Авторизация прошла успешно</h1>");
                out.println("<a href=\"Admin.jsp\"><button type=\"button\">Войти</button></a>");
                out.println("<a href=\"AdminLogin.jsp\"><button type=\"button\">Назад</button></a>");
            }
            else
            {
                out.println("<h1>"+admin.LoginPasswordCheck(entered_login,entered_password)+"</h1>");
                out.println("<a href=\"AdminLogin.jsp\"><button type=\"button\">Назад</button></a>");
            }
        }
        if(pointer.equals("Добавление"))
        {
            String student_id;
            String Name;
            String SerName;
            String StudentGroup;
            String Faculty;
            String baseMark;
            String progMark;
            String webMark;
            String rating;
            student_id= request.getParameter("student_id");
            Name = request.getParameter("Name");
            SerName = request.getParameter("SerName");
            StudentGroup = request.getParameter("StudentGroup");
            Faculty = request.getParameter("Faculty");
            baseMark = request.getParameter("baseMark");
            progMark = request.getParameter("progMark");
            webMark = request.getParameter("webMark");
            rating = request.getParameter("rating");
            Admin admin = new Admin();
            try {
                if(admin.Adder(Integer.parseInt(student_id),Name,SerName,StudentGroup,Faculty,Integer.parseInt(baseMark),Integer.parseInt(progMark),Integer.parseInt(webMark),Double.parseDouble(rating))==1)
                {
                    out.println("<h1>Запись успешно добавлена</h1>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
                else
                {
                    out.println("<h1>Запись с указанным ID уже существует</h1>");
                    out.println("<a href=\"Adder.jsp\"><button type=\"button\">Назад</button></a>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(pointer.equals("Удаление"))
        {
            String student_id;
            student_id= request.getParameter("student_id");
            Admin admin = new Admin();
            try {
                if(admin.Deleter(Integer.parseInt(student_id))==1)
                {
                    out.println("<h1>Запись успешно удалена</h1>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
                else
                {
                    out.println("<h1>Ошибка, ID студента указан неверно</h1>");
                    out.println("<a href=\"Deleter.jsp\"><button type=\"button\">Назад</button></a>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(pointer.equals("Поиск записи по ID"))
        {
            String student_id;
            student_id= request.getParameter("student_id");
            Admin admin = new Admin();
            try {
                if(admin.idCheck(Integer.parseInt(student_id))==1)
                {
                    out.println("<h1>Запись найдена</h1>");
                    out.println("<a href=\"UpdateCurrent.jsp\"><button type=\"button\">Начать редактирование</button></a>");
                }
                else
                {
                    out.println("<h1>Ошибка, ID студента указан неверно</h1>");
                    out.println("<a href=\"Updater.jsp\"><button type=\"button\">Назад</button></a>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(pointer.equals("Редактирование"))
        {
            String student_id;
            String Name;
            String SerName;
            String StudentGroup;
            String Faculty;
            String baseMark;
            String progMark;
            String webMark;
            String rating;
            student_id= request.getParameter("student_id");
            Name = request.getParameter("Name");
            SerName = request.getParameter("SerName");
            StudentGroup = request.getParameter("StudentGroup");
            Faculty = request.getParameter("Faculty");
            baseMark = request.getParameter("baseMark");
            progMark = request.getParameter("progMark");
            webMark = request.getParameter("webMark");
            rating = request.getParameter("rating");
            Admin admin = new Admin();
            try {
                if(admin.Updater(Integer.parseInt(student_id),Name,SerName,StudentGroup,Faculty,Integer.parseInt(baseMark),Integer.parseInt(progMark),Integer.parseInt(webMark),Double.parseDouble(rating))==1)
                {
                    out.println("<h1>Запись успешно изменена</h1>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
                else
                {
                    out.println("<h1>Ошибка</h1>");
                    out.println("<a href=\"Admin.jsp\"><button type=\"button\">Вернуться к просмотру таблицы</button></a>");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
