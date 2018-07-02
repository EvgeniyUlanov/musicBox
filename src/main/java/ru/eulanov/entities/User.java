package ru.eulanov.entities;

public class User {

    private long id;
    private String name;
    private String login;
    private String address;
    private String role;
    private String[] favoriteMusic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(String[] favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return id + " - " + name + ", role - " + role + ", login - " + login;
    }
}
