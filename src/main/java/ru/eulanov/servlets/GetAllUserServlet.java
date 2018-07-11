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

/**
 * servlet for getting users by query
 */
public class GetAllUserServlet extends HttpServlet {

    private UserStore userStore;

    @Override
    public void init() throws ServletException {
        userStore = UserStore.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedCategory = req.getParameter("selectedCategory");
        String whatNeedToFind = req.getParameter("whatNeedToFind");
        List<User> users = null;
        if (selectedCategory != null) {
            switch (selectedCategory) {
                case "name":
                    users = userStore.findUserByName(whatNeedToFind);
                    break;
                case "login":
                    users = userStore.findUserByLogin(whatNeedToFind);
                    break;
                case "role":
                    users = userStore.findUserByRole(whatNeedToFind);
                    break;
                case "address":
                    users = userStore.findUserByAddress(whatNeedToFind);
                    break;
                case "music":
                    users = userStore.findUserByMusic(whatNeedToFind);
                    break;
                default:
                    users = userStore.getAllUsers();
            }
        }
        Gson gson = new Gson();
        String usersGson = gson.toJson(users);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(usersGson);
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }
}