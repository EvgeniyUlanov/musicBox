package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.entities.Music;
import ru.eulanov.stores.MusicStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddMusicTypeServletTest {

    @Test
    public void testGetParametersFromRequestAndSendRequestToMusicStore() throws ServletException, IOException {
        AddMusicTypeServlet servlet = new AddMusicTypeServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        MusicStore ms = mock(MusicStore.class);

        when(req.getParameter("newMusicType")).thenReturn("rock");
        servlet.setMusicStore(ms);
        servlet.doPost(req, resp);
        Music music = new Music();
        music.setMusicType("rock");

        verify(ms).addMusicType(eq(music));
    }
}
