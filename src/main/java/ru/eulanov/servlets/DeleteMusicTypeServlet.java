package ru.eulanov.servlets;

import ru.eulanov.stores.MusicTypeStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMusicTypeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("musicId"));
            MusicTypeStore.getInstance().deleteMusicType(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
