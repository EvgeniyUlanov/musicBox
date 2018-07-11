package ru.eulanov.stores;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eulanov.InitDataBase;
import ru.eulanov.entities.Music;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MusicStoreTest {
    @BeforeClass
    public static void initDB() {
        InitDataBase.createTableMusicTypes();
    }

    @Test
    public void testAddGetDeleteMusic() {
        MusicStore musicStore = MusicStore.getInstance();
        Music music = new Music();
        music.setMusicType("pop");
        musicStore.addMusicType(music);

        List<Music> musicList = musicStore.getAll();

        assertTrue(musicList.contains(music));

        for (Music music1 : musicList) {
            if (music1.getMusicType().equals("pop")) {
                musicStore.deleteMusicType(music1.getId());
            }
        }

        musicList = musicStore.getAll();

        assertTrue(!musicList.contains(music));
    }
}
