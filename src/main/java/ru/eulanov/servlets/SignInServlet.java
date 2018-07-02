package ru.eulanov.servlets;

import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user = UserStore.getInstance().getUserByLogin(login);
        String password = req.getParameter("password");
        if (user.getLogin() != null && user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            req.getRequestDispatcher("WEB-INF/views/adminPage.jsp").forward(req, resp);
        } else {
            doGet(req, resp);
        }
    }
}
