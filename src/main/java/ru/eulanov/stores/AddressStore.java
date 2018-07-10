package ru.eulanov.stores;

import ru.eulanov.connectionpool.DBConnectionPool;
import ru.eulanov.entities.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressStore {
    private final static AddressStore ADDRESS_STORE = new AddressStore();

    /**
     * constructor.
     */
    private AddressStore() {
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
    public static AddressStore getInstance() {
        return ADDRESS_STORE;
    }

    public List<String> getAllAddress() {
        List<String> address = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection(); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT addres FROM address");
            while (rs.next()) {
                address.add(rs.getString("addres"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public void addAddress(Address address) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO address (addres) VALUES (?);")) {
            ps.setString(1, address.getAddress());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(Address address) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM address WHERE id = ?;")) {
            ps.setLong(1, address.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(Address address) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE address SET addres = ? WHERE id = ?")) {
            ps.setString(1, address.getAddress());
            ps.setLong(2, address.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
