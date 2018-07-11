package ru.eulanov.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignOutServletTest {

    @Test
    public void testWhenSignOutThanSessionClosedAndRedirectToDefault() throws IOException, ServletException {
        SignOutServlet servlet = new SignOutServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("WEB-INF");

        servlet.doGet(req, resp);

        verify(req.getSession()).invalidate();
        verify(resp).sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
