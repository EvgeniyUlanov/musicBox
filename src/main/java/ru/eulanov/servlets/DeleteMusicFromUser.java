package ru.eulanov.servlets;

import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet remove music type from users favorite music list.
 */
public class DeleteMusicFromUser extends HttpServlet {

    /**
     * method doGet
     * @param req - request
     * @param resp - response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String musicType = req.getParameter("musicType");
        UserStore.getInstance().deleteMusicFromUser(userId, musicType);
    }
}
