package ru.eulanov.stores;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eulanov.InitDataBase;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RolesStoreTest {
    @BeforeClass
    public static void initDB() {
        InitDataBase.createTableRoles();
    }

    @Test
    public void testGetAllRoles() {
        List<String> roles = RoleStore.getInstance().getAllRoles();

        assertTrue(roles.contains("admin"));
        assertTrue(roles.contains("user"));
    }
}
