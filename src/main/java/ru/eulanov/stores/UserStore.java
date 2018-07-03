package ru.eulanov.stores;

import ru.eulanov.connectionpool.DBConnectionPool;
import ru.eulanov.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserStore {

    /**
     * instance of user store.
     */
    private final static UserStore USER_STORE = new UserStore();

    /**
     * constructor.
     */
    private UserStore() {
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
    public static UserStore getInstance() {
        return USER_STORE;
    }

    public User getUserById(long id) {
        User user = new User();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users "
                     + "INNER JOIN roles ON users.role_id = roles.id "
                     + "LEFT JOIN address ON users.address_id = address.id WHERE users.id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("addres"));
                user.setRole(rs.getString("role_name"));
                user.setFavoriteMusic(getFavoriteUserMusic(user.getId()));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users "
                             + "INNER JOIN roles ON users.role_id = roles.id "
                             + "LEFT JOIN address ON users.address_id = address.id")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("addres"));
                user.setRole(rs.getString("role_name"));
                user.setFavoriteMusic(getFavoriteUserMusic(user.getId()));
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<String> getFavoriteUserMusic(long id) {
        List<String> favoriteMusic = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT music_types.music_type FROM music_prefers "
                             + "INNER JOIN music_types ON music_prefers.music_type_id = music_types.id WHERE user_id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                favoriteMusic.add(rs.getString("music_type"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return favoriteMusic;
    }

    public User getUserForLogin(String login) {
        User user = new User();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT login, password, role_name FROM users "
                             + "INNER JOIN roles ON users.role_id = roles.id "
                             + "LEFT JOIN address ON users.address_id = address.id WHERE users.login = ?")) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role_name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
