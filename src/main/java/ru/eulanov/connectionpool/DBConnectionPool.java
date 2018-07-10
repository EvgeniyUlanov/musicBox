package ru.eulanov.connectionpool;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

/**
 * class for getting and close connections
 * singleton
 */
public class DBConnectionPool {

    /**
     * instance of db source.
     */
    private static BasicDataSource dbSource;

    /**
     * constructor
     */
    private DBConnectionPool() {
    }

    /**
     * method for get instance of db source to receive connection to database.
     * @return instance of db source
     * @throws ClassNotFoundException - exception throw if some problem with database
     */
    public static synchronized BasicDataSource getDbSource() throws ClassNotFoundException {
        if (dbSource == null) {
            Class.forName("org.postgresql.Driver");
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:postgresql://localhost:5432/musicbox");
            ds.setUsername("postgres");
            ds.setPassword("784512963");
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
            dbSource = ds;
        }
        return dbSource;
    }

    /**
     * method close connection to database.
     */
    public static synchronized void closeConnection() {
        if (dbSource != null) {
            try {
                dbSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
