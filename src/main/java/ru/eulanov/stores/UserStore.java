package ru.eulanov.stores;

import ru.eulanov.connectionpool.DBConnectionPool;
import ru.eulanov.entities.MusicType;
import ru.eulanov.entities.User;

import java.sql.*;
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

    private List<String> getFavoriteUserMusic(long userId) {
        List<String> favoriteMusic = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT music_types.music_type FROM music_prefers "
                             + "INNER JOIN music_types ON music_prefers.music_type_id = music_types.id WHERE user_id = ?")) {
            ps.setLong(1, userId);
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

    public boolean deleteUser(long userId) {
        boolean result = false;
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM users WHERE id = ?;")) {
            ps.setLong(1, userId);
            result = ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addUser(User user) {
        boolean result = false;
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users " +
                     "(name, login, password, role_id, address_id) VALUES (" +
                     "?, " +
                     "?, " +
                     "?, " +
                     "(SELECT roles.id FROM roles WHERE role_name = ?), " +
                     "(SELECT address.id FROM address WHERE addres = ?))")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getAddress());
            result = ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean setMusicPrefToUser(long userId, String musicType) {
        boolean result = false;
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO music_prefers VALUES (?, (SELECT mt.id FROM music_types AS mt WHERE music_type = ?));")) {
            ps.setLong(1, userId);
            ps.setString(2, musicType);
            result = ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(User user) {
        boolean result = false;
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE users SET " +
                     "name = ?, " +
                     "login = ?," +
                     "password = ?, " +
                     "role_id = (SELECT roles.id FROM roles WHERE role_name = ?), " +
                     "address_id = (SELECT address.id FROM address WHERE addres = ?) " +
                     "WHERE id = ?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getAddress());
            ps.setLong(6, user.getId());
            result = ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteMusicFromUser(Long userId, String musicType) {
        boolean result = false;
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM music_prefers " +
                             "WHERE user_id = ? AND music_type_id = (SELECT id FROM music_types WHERE music_type = ?);")) {
            ps.setLong(1, userId);
            ps.setString(2, musicType);
            result = ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> findUserByName(String name) {
        List<User> userList = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users " +
                             "INNER JOIN roles ON users.role_id = roles.id " +
                             "LEFT JOIN address ON users.address_id = address.id WHERE users.name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(fillUserFromResultSet(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> findUserByLogin(String login) {
        List<User> userList = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users " +
                             "INNER JOIN roles ON users.role_id = roles.id " +
                             "LEFT JOIN address ON users.address_id = address.id WHERE users.login = ?")) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(fillUserFromResultSet(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private User fillUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role_name"));
        user.setAddress(rs.getString("addres"));
        return user;
    }

    public List<User> findUserByRole(String role) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users " +
                             "INNER JOIN roles ON users.role_id = roles.id " +
                             "LEFT JOIN address ON users.address_id = address.id WHERE role_name = ?")) {
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(fillUserFromResultSet(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findUserByAddress(String address) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users " +
                             "INNER JOIN roles ON users.role_id = roles.id " +
                             "LEFT JOIN address ON users.address_id = address.id WHERE addres = ?")) {
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(fillUserFromResultSet(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findUserByMusic(String musicType) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.id, name, login, password, addres, role_name FROM users " +
                             "INNER JOIN roles ON users.role_id = roles.id " +
                             "LEFT JOIN address ON users.address_id = address.id " +
                             "WHERE users.id IN " +
                                "(SELECT user_id FROM music_prefers WHERE music_type_id IN " +
                                    "(SELECT id FROM music_types WHERE music_type = ?));")) {
            ps.setString(1, musicType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(fillUserFromResultSet(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
