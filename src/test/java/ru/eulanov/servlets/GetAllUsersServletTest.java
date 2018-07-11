package ru.eulanov.servlets;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.eulanov.entities.Music;
import ru.eulanov.entities.User;
import ru.eulanov.stores.MusicStore;
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

public class GetAllUsersServletTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private PrintWriter writer;
    private UserStore userStore;
    private GetAllUserServlet servlet;
    private User mike;
    private User peter;
    private User ivan;

    @Before
    public void init() throws IOException {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);
        userStore = mock(UserStore.class);
        servlet = new GetAllUserServlet();
        servlet.setUserStore(userStore);
        mike = receiveUser(1, "mike", "logM", "user", "street", "1");
        mike.setFavoriteMusic(Arrays.asList("rock", "rap"));
        peter = receiveUser(2, "peter", "logP", "user", "address", "1");
        peter.setFavoriteMusic(Arrays.asList("rock", "rap"));
        ivan = receiveUser(3, "ivan", "logI", "admin", "avenue", "1");
        when(resp.getWriter()).thenReturn(writer);
    }

    @Test
    public void testFindAllUsersAndReturnJsonAnswer() throws ServletException, IOException {
        when(req.getParameter("selectedCategory")).thenReturn("");
        when(req.getParameter("whatNeedToFind")).thenReturn("");
        when(userStore.getAllUsers()).thenReturn(Arrays.asList(mike, peter));

        servlet.doPost(req, resp);

        verify(userStore).getAllUsers();
        verify(writer).write(
                "[{\"id\":1,\"name\":\"mike\",\"login\":\"logM\",\"address\":\"street\",\"role\":\"user\"," +
                        "\"password\":\"1\",\"favoriteMusic\":[\"rock\",\"rap\"]}," +
                        "{\"id\":2,\"name\":\"peter\",\"login\":\"logP\",\"address\":\"address\",\"role\":\"user\"," +
                        "\"password\":\"1\",\"favoriteMusic\":[\"rock\",\"rap\"]}]");
    }

    @Test
    public void testGetCategoryAndValueNameFromRequestAndSendToUserStore() throws ServletException, IOException {
        when(req.getParameter("selectedCategory")).thenReturn("name");
        when(req.getParameter("whatNeedToFind")).thenReturn("ivan");
        when(userStore.findUserByName("ivan")).thenReturn(Arrays.asList(ivan));

        servlet.doPost(req, resp);

        verify(userStore).findUserByName("ivan");
        verify(resp.getWriter()).write("[{\"id\":3,\"name\":\"ivan\",\"login\":\"logI\"," +
                "\"address\":\"avenue\",\"role\":\"admin\",\"password\":\"1\"}]");
    }

    @Test
    public void testGetCategoryAndValueLoginFromRequestAndSendToUserStore() throws ServletException, IOException {
        when(req.getParameter("selectedCategory")).thenReturn("login");
        when(req.getParameter("whatNeedToFind")).thenReturn("logI");
        when(userStore.findUserByLogin("logI")).thenReturn(Arrays.asList(ivan));

        servlet.doPost(req, resp);

        verify(userStore).findUserByLogin("logI");
        verify(resp.getWriter()).write("[{\"id\":3,\"name\":\"ivan\",\"login\":\"logI\"," +
                "\"address\":\"avenue\",\"role\":\"admin\",\"password\":\"1\"}]");
    }

    @Test
    public void testGetCategoryAndValueRoleFromRequestAndSendToUserStore() throws ServletException, IOException {
        when(req.getParameter("selectedCategory")).thenReturn("role");
        when(req.getParameter("whatNeedToFind")).thenReturn("admin");

        servlet.doPost(req, resp);

        verify(userStore).findUserByRole("admin");
    }

    @Test
    public void testGetCategoryAndValueAddressFromRequestAndSendToUserStore() throws ServletException, IOException {
        when(req.getParameter("selectedCategory")).thenReturn("address");
        when(req.getParameter("whatNeedToFind")).thenReturn("avenue");

        servlet.doPost(req, resp);

        verify(userStore).findUserByAddress("avenue");
    }

    @Test
    public void testGetCategoryAndValueMusicFromRequestAndSendToUserStore() throws ServletException, IOException {
        when(req.getParameter("selectedCategory")).thenReturn("music");
        when(req.getParameter("whatNeedToFind")).thenReturn("rap");

        servlet.doPost(req, resp);

        verify(userStore).findUserByMusic("rap");
    }

    private User receiveUser(long id, String name, String login, String role, String address, String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLogin(login);
        user.setRole(role);
        user.setAddress(address);
        user.setPassword(password);
        return user;
    }
}
