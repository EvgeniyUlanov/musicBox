package ru.eulanov.entities;

import java.util.List;

/**
 * class for entity user.
 */
public class User {
    /** user id*/
    private long id;
    /** user name*/
    private String name;
    /** user login*/
    private String login;
    /** user address*/
    private String address;
    /** user role*/
    private String role;
    /** user password*/
    private String password;
    /** user's favorite music*/
    private List<String> favoriteMusic;

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

    public List<String> getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(List<String> favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
