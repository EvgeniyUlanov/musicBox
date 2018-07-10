package ru.eulanov.stores;

import ru.eulanov.connectionpool.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleStore {

    private final static RoleStore ROLE_STORE = new RoleStore();

    /**
     * constructor.
     */
    private RoleStore() {
        try {
            Class.forName("ru.eulanov.connectionpool.DBConnectionPool");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method return instance of user store.
     *
     * @return - UserStore.
     */
    public static RoleStore getInstance() {
        return ROLE_STORE;
    }

    public List<String> getAllRoles() {
        List<String> roles = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection(); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT role_name FROM roles");
            while (rs.next()) {
                roles.add(rs.getString("role_name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
