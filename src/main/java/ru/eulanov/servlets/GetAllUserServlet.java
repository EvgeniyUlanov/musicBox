package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedCategory = req.getParameter("selectedCategory");
        String whatNeedToFind = req.getParameter("whatNeedToFind");
        List<User> users = null;
        if (selectedCategory != null) {
            switch (selectedCategory) {
                case "name":
                    users = UserStore.getInstance().findUserByName(whatNeedToFind);
                    break;
                case "login":
                    users = UserStore.getInstance().findUserByLogin(whatNeedToFind);
                    break;
                case "role":
                    users = UserStore.getInstance().findUserByRole(whatNeedToFind);
                    break;
                case "address":
                    users = UserStore.getInstance().findUserByAddress(whatNeedToFind);
                    break;
                case "music":
                    users = UserStore.getInstance().findUserByMusic(whatNeedToFind);
                    break;
                default:
                    users = UserStore.getInstance().getAllUsers();
            }
        }
        Gson gson = new Gson();
        String usersGson = gson.toJson(users);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(usersGson);
    }
}