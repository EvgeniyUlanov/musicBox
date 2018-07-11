package ru.eulanov.servlets;

import org.junit.Test;
import ru.eulanov.entities.Music;
import ru.eulanov.stores.MusicStore;
import ru.eulanov.stores.RoleStore;

import javax.management.relation.Role;
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

public class GetRolesServletTest {
    @Test
    public void testGetAllRoleFromRolesStoreAndSendItToUserAsGson() throws ServletException, IOException {
        GetRolesServlet servlet = new GetRolesServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        RoleStore roleStore = mock(RoleStore.class);
        List<String> roles = Arrays.asList("user", "admin");

        servlet.setRoleStore(roleStore);
        when(roleStore.getAllRoles()).thenReturn(roles);
        when(resp.getWriter()).thenReturn(writer);

        servlet.doGet(req, resp);

        verify(roleStore).getAllRoles();
        verify(resp.getWriter()).write("[\"user\",\"admin\"]");
    }
}
