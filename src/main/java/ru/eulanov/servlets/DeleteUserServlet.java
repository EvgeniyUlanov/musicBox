package ru.eulanov.servlets;

import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet do delete user from database
 */
public class DeleteUserServlet extends HttpServlet {
    /**
     * method doGet delete user by user id
     * @param req - request
     * @param resp - response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(req.getParameter("userId"));
            UserStore.getInstance().deleteUser(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
