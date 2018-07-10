package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.MusicType;
import ru.eulanov.stores.MusicTypeStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllMusicTypesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MusicType> musicTypes = MusicTypeStore.getInstance().getAll();
        Gson gson = new Gson();
        String musicTypesJson = gson.toJson(musicTypes);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(musicTypesJson);
    }
}
