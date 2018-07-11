package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignInServletTest {
    @Test
    public void testWhenLoginAndPasswordCorrectThanRedirectToController() throws ServletException, IOException {
        SignInServlet servlet = new SignInServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        UserStore userStore = mock(UserStore.class);
        HttpSession session = mock(HttpSession.class);
        servlet.setUserStore(userStore);
        User user = new User();
        user.setLogin("login");
        user.setPassword("1");

        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("1");
        when(req.getContextPath()).thenReturn("WEB-INF");
        when(req.getSession()).thenReturn(session);
        when(userStore.getUserForLogin("login")).thenReturn(user);

        servlet.doPost(req, resp);

        verify(userStore).getUserForLogin("login");
        verify(resp).sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Test
    public void testWhenLoginAndPasswordWrongThanRedirectToSignIn() throws ServletException, IOException {
        SignInServlet servlet = new SignInServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        UserStore userStore = mock(UserStore.class);
        servlet.setUserStore(userStore);
        User user = new User();
        user.setLogin("login");
        user.setPassword("2");

        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("1");
        when(req.getRequestDispatcher("/WEB-INF/views/signin.jsp")).thenReturn(dispatcher);
        when(userStore.getUserForLogin("login")).thenReturn(user);

        servlet.doPost(req, resp);

        verify(userStore).getUserForLogin("login");
        verify(dispatcher).forward(req, resp);
    }
}
