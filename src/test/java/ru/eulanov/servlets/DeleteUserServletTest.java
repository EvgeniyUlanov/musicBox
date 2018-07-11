package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteUserServletTest {

    @Test
    public void testGetIdParametrFromRequestAndSendItToUserStoreToDelete() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        UserStore us = mock(UserStore.class);
        DeleteUserServlet servlet = new DeleteUserServlet();

        when(req.getParameter("userId")).thenReturn("1");
        servlet.setUserStore(us);

        servlet.doGet(req, resp);

        verify(us).deleteUser(1L);
    }
}
