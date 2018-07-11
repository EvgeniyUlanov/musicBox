package ru.eulanov.servlets;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMusicToUserServletTest {

    @Test
    public void testThatMethodReceiveParametersAndSendRequestToUserStore() throws ServletException, IOException {
        AddMusicToUserServlet servlet = new AddMusicToUserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        UserStore us = mock(UserStore.class);

        when(req.getParameter("userId")).thenReturn("1");
        when(req.getParameter("musicType")).thenReturn("rock");
        servlet.setUserStore(us);
        servlet.doPost(req, resp);

        verify(us).setMusicPrefToUser(Long.parseLong(req.getParameter("userId")), req.getParameter("musicType"));
    }
}
