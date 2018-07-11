package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.entities.Music;
import ru.eulanov.stores.MusicStore;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllMusicTypesServletTest {

    @Test
    public void testGetAllMusicFromMusicStoreAndSendItToUserAsGson() throws ServletException, IOException {
        GetAllMusicTypesServlet servlet = new GetAllMusicTypesServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        MusicStore musicStore = mock(MusicStore.class);
        Music rock = new Music();
        rock.setMusicType("rock");
        rock.setId(1);
        Music rap = new Music();
        rap.setMusicType("rap");
        rap.setId(2);
        List<Music> musicList = Arrays.asList(rock, rap);

        servlet.setMusicStore(musicStore);
        when(musicStore.getAll()).thenReturn(musicList);
        when(resp.getWriter()).thenReturn(writer);

        servlet.doGet(req, resp);

        verify(resp.getWriter()).write("[{\"id\":1,\"musicType\":\"rock\"},{\"id\":2,\"musicType\":\"rap\"}]");
    }
}
