package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        User user = UserStore.getInstance().getUserById(userId);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(userJson);
    }
}
