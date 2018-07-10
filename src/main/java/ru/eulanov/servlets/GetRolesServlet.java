package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.stores.RoleStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetRolesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> roles = RoleStore.getInstance().getAllRoles();
        Gson gson = new Gson();
        String rolesJson = gson.toJson(roles);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(rolesJson);
    }
}