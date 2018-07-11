package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet to get user by id.
 */
public class GetUserServlet extends HttpServlet{

    private UserStore userStore;

    @Override
    public void init() throws ServletException {
        userStore = UserStore.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        User user = userStore.getUserById(userId);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        System.out.println(userJson);
        resp.getWriter().write(userJson);
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }
}
