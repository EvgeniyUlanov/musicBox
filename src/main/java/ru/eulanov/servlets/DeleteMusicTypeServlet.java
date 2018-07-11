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
public class DeleteMusicTypeServlet extends HttpServlet {

    private MusicStore musicStore;

    @Override
    public void init() throws ServletException {
        musicStore = MusicStore.getInstance();
    }

    /**
     * method doGet deletes music by music id
     *
     * @param req  - request
     * @param resp - response
     * @throws ServletException - servlet exception
     * @throws IOException      - io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("musicId"));
        musicStore.deleteMusicType(id);
    }

    public MusicStore getMusicStore() {
        return musicStore;
    }

    public void setMusicStore(MusicStore musicStore) {
        this.musicStore = musicStore;
    }
}
