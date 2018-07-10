package ru.eulanov.stores;

import ru.eulanov.connectionpool.DBConnectionPool;
import ru.eulanov.entities.MusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeStore {

    private static final MusicTypeStore MUSIC_TYPE_STORE = new MusicTypeStore();

    private MusicTypeStore() {
        try {
            Class.forName("ru.eulanov.connectionpool.DBConnectionPool");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static MusicTypeStore getInstance() {
        return MUSIC_TYPE_STORE;
    }

    public List<MusicType> getAll() {
        List<MusicType> musicTypes = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM music_types")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MusicType musicType = new MusicType();
                musicType.setId(rs.getLong("id"));
                musicType.setMusicType(rs.getString("music_type"));
                musicTypes.add(musicType);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return musicTypes;
    }

    public void addMusicType(MusicType musicType) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO music_types (music_type) VALUES (?)")) {
            ps.setString(1, musicType.getMusicType());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMusicType(long id) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM music_types WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public MusicType getMusicTypeById(long id) {
        MusicType musicType = new MusicType();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM music_types WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                musicType.setId(rs.getLong("id"));
                musicType.setMusicType("music_type");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return musicType;
    }
}