package ru.eulanov.servlets;

import ru.eulanov.InitDataBase;
import ru.eulanov.connectionpool.DBConnectionPool;
import ru.eulanov.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * user controller.
 * init data base connection
 * send user to admin or user page, depended of user role
 * close connection to database
 */
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getRole().equals("admin")) {
            req.getRequestDispatcher("WEB-INF/views/adminPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/views/userPage.jsp").forward(req, resp);
        }
    }

    @Override
    public void init() throws ServletException {
        InitDataBase.createTableUsers();
        InitDataBase.createTableRoles();
        InitDataBase.createTableAddress();
        InitDataBase.createTableMusicTypes();
        InitDataBase.createTableMusicPreferes();
    }

    @Override
    public void destroy() {
        DBConnectionPool.closeConnection();
    }
}
