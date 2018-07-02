package ru.eulanov.servlets;

import ru.eulanov.InitDataBase;
import ru.eulanov.connectionpool.DBConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/signin.jsp").forward(req, resp);
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
