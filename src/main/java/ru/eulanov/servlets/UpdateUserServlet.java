package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;
import ru.eulanov.utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet for update user
 */
public class UpdateUserServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String incomingString = MyUtil.getIncomingStringFromReqest(req);
        Gson gson = new Gson();
        User user = gson.fromJson(incomingString, User.class);
        UserStore.getInstance().updateUser(user);
    }
}
