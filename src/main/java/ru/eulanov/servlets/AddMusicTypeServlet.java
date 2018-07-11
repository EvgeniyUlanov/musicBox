package ru.eulanov.servlets;

import ru.eulanov.entities.Music;
import ru.eulanov.stores.MusicStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet for add music type to database.
 */
public class AddMusicTypeServlet extends HttpServlet {

    private MusicStore musicStore;

    @Override
    public void init() throws ServletException {
        musicStore = MusicStore.getInstance();
    }

    /**
     * method doPost.
     * @param req - http request
     * @param resp - http response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Music musicType = new Music();
        musicType.setMusicType(req.getParameter("newMusicType"));
        musicStore.addMusicType(musicType);
    }

    public MusicStore getMusicStore() {
        return musicStore;
    }

    public void setMusicStore(MusicStore musicStore) {
        this.musicStore = musicStore;
    }
}
