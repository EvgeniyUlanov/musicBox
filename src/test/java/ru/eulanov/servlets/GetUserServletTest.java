package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.entities.User;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserServletTest {
    @Test
    public void testGetUserByIdFromUserStoreAndSendItToUserAsGson() throws ServletException, IOException {
        GetUserServlet servlet = new GetUserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        UserStore userStore = mock(UserStore.class);

        User user = new User();
        user.setId(1);
        user.setName("ivan");
        user.setLogin("admin");
        user.setRole("admin");
        user.setAddress("address");
        user.setPassword("1");
        user.setFavoriteMusic(Arrays.asList("rock", "electro", "rap"));
        servlet.setUserStore(userStore);

        when(req.getParameter("userId")).thenReturn("1");
        when(userStore.getUserById(1)).thenReturn(user);
        when(resp.getWriter()).thenReturn(writer);

        servlet.doGet(req, resp);

        verify(userStore).getUserById(1);
        verify(resp.getWriter()).write(
                "{\"id\":1,\"name\":\"ivan\",\"login\":\"admin\",\"address\":\"address\"," +
                        "\"role\":\"admin\",\"password\":\"1\",\"favoriteMusic\":[\"rock\",\"electro\",\"rap\"]}");
    }
}
