package ru.eulanov.servlets;

import ru.eulanov.stores.MusicStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet delete music type from database
 */
public class DeleteMusicTypeServlet extends HttpServlet{

    /**
     * method doGet deletes music by music id
     * @param req - request
     * @param resp - response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("musicId"));
            MusicStore.getInstance().deleteMusicType(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
