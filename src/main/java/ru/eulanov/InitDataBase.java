package ru.eulanov;

import ru.eulanov.connectionpool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {

    public static void createTableUsers() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users;");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "login VARCHAR(255), " +
                    "password VARCHAR(255)," +
                    "role_id INTEGER, " +
                    "address_id INTEGER);");
            st.executeUpdate("INSERT INTO users VALUES (1, 'ivan', 'admin', '1', 1, 1);");
            st.executeUpdate("INSERT INTO users VALUES (2, 'vasia', 'user', '2', 2, 1);");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableRoles() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS roles");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS roles (" +
                    "id INTEGER PRIMARY KEY, " +
                    "role_name VARCHAR(255));");
            st.executeUpdate("INSERT INTO roles VALUES (1, 'admin');");
            st.executeUpdate("INSERT INTO roles VALUES (2, 'user');");
            st.executeUpdate("INSERT INTO roles VALUES (3, 'mandator');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableAddress() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS address");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS address (" +
                    "id INTEGER PRIMARY KEY, " +
                    "addres VARCHAR(255));");
            st.executeUpdate("INSERT INTO address VALUES (1, 'velikiy-novgorod');");
            st.executeUpdate("INSERT INTO address VALUES (2, 'moscow');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableMusicTypes() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS music_types");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS music_types (" +
                    "id INTEGER PRIMARY KEY, " +
                    "music_type VARCHAR(255));");
            st.executeUpdate("INSERT INTO music_types VALUES (1, 'rock');");
            st.executeUpdate("INSERT INTO music_types VALUES (2, 'electro');");
            st.executeUpdate("INSERT INTO music_types VALUES (3, 'rap');");
            st.executeUpdate("INSERT INTO music_types VALUES (4, 'ballads');");
            st.executeUpdate("INSERT INTO music_types VALUES (5, 'techno');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableMusicPreferes() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS music_prefers");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS music_prefers (" +
                    "user_id INTEGER, " +
                    "music_type_id INTEGER);");
            st.executeUpdate("INSERT INTO music_prefers VALUES (1, 1);");
            st.executeUpdate("INSERT INTO music_prefers VALUES (1, 2);");
            st.executeUpdate("INSERT INTO music_prefers VALUES (1, 3);");
            st.executeUpdate("INSERT INTO music_prefers VALUES (2, 4);");
            st.executeUpdate("INSERT INTO music_prefers VALUES (2, 5);");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
