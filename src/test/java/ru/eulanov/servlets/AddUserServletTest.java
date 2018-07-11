package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.entities.Address;
import ru.eulanov.entities.User;
import ru.eulanov.stores.AddressStore;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddUserServletTest {

    @Test
    public void testGetParametersFromRequestAndSendRequestToMusicStore() throws ServletException, IOException {
        AddUserServlet servlet = new AddUserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        UserStore userStore = mock(UserStore.class);
        AddressStore addressStore = mock(AddressStore.class);

        String userJson =
                "{\"name\":\"user\",\"login\":\"login\",\"password\":\"1\",\"role\":\"user\",\"address\":\"address\"}";
        when(req.getReader()).thenReturn(
                new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userJson.getBytes())))
        );
        servlet.setUserStore(userStore);
        servlet.setAddressStore(addressStore);
        servlet.doPost(req, resp);

        User user = new User();
        user.setName("user");
        user.setLogin("login");
        user.setPassword("password");
        user.setRole("user");
        user.setAddress("address");
        Address address = new Address();
        address.setAddress("address");

        verify(userStore).addUser(eq(user));
        verify(addressStore).addAddress(eq(address));
    }
}
