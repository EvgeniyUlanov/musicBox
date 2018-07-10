package ru.eulanov.servlets;

import ru.eulanov.entities.MusicType;
import ru.eulanov.stores.MusicTypeStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMusicTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MusicType musicType = new MusicType();
        musicType.setMusicType(req.getParameter("newMusicType"));
        MusicTypeStore.getInstance().addMusicType(musicType);
    }
}
