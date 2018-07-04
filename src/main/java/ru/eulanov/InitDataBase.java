package ru.eulanov;

import ru.eulanov.connectionpool.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {

    public static void createTableUsers() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users CASCADE;");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "login VARCHAR(255) UNIQUE, " +
                    "password VARCHAR(255)," +
                    "role_id INTEGER REFERENCES roles(id), " +
                    "address_id INTEGER REFERENCES address(id));");
            st.executeUpdate("INSERT INTO users (name, login, password, role_id, address_id) VALUES ('ivan', 'admin', '1', 1, 1);");
            st.executeUpdate("INSERT INTO users (name, login, password, role_id, address_id) VALUES ('vasia', 'user', '2', 2, 1);");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableRoles() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS roles CASCADE");
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
            st.executeUpdate("DROP TABLE IF EXISTS address CASCADE");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS address (" +
                    "id SERIAL PRIMARY KEY, " +
                    "addres VARCHAR(255));");
            st.executeUpdate("INSERT INTO address (addres) VALUES ('velikiy-novgorod');");
            st.executeUpdate("INSERT INTO address (addres) VALUES ('moscow');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableMusicTypes() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS music_types CASCADE");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS music_types (" +
                    "id SERIAL PRIMARY KEY, " +
                    "music_type VARCHAR(255));");
            st.executeUpdate("INSERT INTO music_types (music_type) VALUES ('rock');");
            st.executeUpdate("INSERT INTO music_types (music_type) VALUES ('electro');");
            st.executeUpdate("INSERT INTO music_types (music_type) VALUES ('rap');");
            st.executeUpdate("INSERT INTO music_types (music_type) VALUES ('ballads');");
            st.executeUpdate("INSERT INTO music_types (music_type) VALUES ('techno');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTableMusicPreferes() {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS music_prefers CASCADE");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS music_prefers (" +
                    "user_id INTEGER NOT NULL REFERENCES users(id), " +
                    "music_type_id INTEGER NOT NULL REFERENCES music_types(id), " +
                    "UNIQUE (user_id, music_type_id));");
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
