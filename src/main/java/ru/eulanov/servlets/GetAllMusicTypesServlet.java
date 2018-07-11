package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.Music;
import ru.eulanov.stores.MusicStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * servlet for getting all music types
 */
public class GetAllMusicTypesServlet extends HttpServlet {

    private MusicStore musicStore;

    @Override
    public void init() throws ServletException {
        musicStore = MusicStore.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Music> musicTypes = musicStore.getAll();
        Gson gson = new Gson();
        String musicTypesJson = gson.toJson(musicTypes);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(musicTypesJson);
    }

    public MusicStore getMusicStore() {
        return musicStore;
    }

    public void setMusicStore(MusicStore musicStore) {
        this.musicStore = musicStore;
    }
}
