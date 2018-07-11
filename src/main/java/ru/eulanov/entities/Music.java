package ru.eulanov.entities;

/**
 * class for entity music type
 */
public class Music {
    /** music type id*/
    private long id;
    /** music type*/
    private String musicType;

    /**
     * get method for id
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * set method for id
     * @param id - id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * get method for music type
     * @return - music type.
     */
    public String getMusicType() {
        return musicType;
    }

    /**
     * set method for music type.
     * @param musicType - music type.
     */
    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Music)) return false;

        Music music = (Music) o;

        return musicType != null ? musicType.equals(music.musicType) : music.musicType == null;
    }

    @Override
    public int hashCode() {
        return musicType != null ? musicType.hashCode() : 0;
    }
}
