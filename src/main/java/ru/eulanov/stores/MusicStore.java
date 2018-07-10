package ru.eulanov.stores;

import ru.eulanov.connectionpool.DBConnectionPool;
import ru.eulanov.entities.Music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class for music crud operations
 */
public class MusicStore {
    /** instance of music store*/
    private static final MusicStore MUSIC_STORE = new MusicStore();

    /**
     * constructor
     */
    private MusicStore() {
        try {
            Class.forName("ru.eulanov.connectionpool.DBConnectionPool");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to get instance of music store
     * @return instance of music store
     */
    public static MusicStore getInstance() {
        return MUSIC_STORE;
    }

    /**
     * method return all music types.
     * @return list of music types.
     */
    public List<Music> getAll() {
        List<Music> musicTypes = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM music_types")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Music musicType = new Music();
                musicType.setId(rs.getLong("id"));
                musicType.setMusicType(rs.getString("music_type"));
                musicTypes.add(musicType);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return musicTypes;
    }

    /**
     * method add music type
     * @param musicType - music type to add.
     */
    public void addMusicType(Music musicType) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO music_types (music_type) VALUES (?)")) {
            ps.setString(1, musicType.getMusicType());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * method delete music type
     * @param id - music type id
     */
    public void deleteMusicType(long id) {
        try (Connection conn = DBConnectionPool.getDbSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM music_types WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * method return music type by id
     * @param id - music type id
     * @return - music type.
     */
    public Music getMusicTypeById(long id) {
        Music musicType = new Music();
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