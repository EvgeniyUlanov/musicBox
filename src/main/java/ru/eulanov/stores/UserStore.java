package ru.eulanov.stores;

import ru.eulanov.entities.User;

public class UserStore {

    /**
     * instance of user store.
     */
    private final static UserStore USER_STORE = new UserStore();

    /**
     * constructor.
     */
    private UserStore() {
        try {
            Class.forName("ru.job4j.connectionpool.DBConnectionPool");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method return instance of user store.
     *
     * @return - UserStore.
     */
    public static UserStore getInstance() {
        return USER_STORE;
    }

    public User getUserById(long id) {
        User result = new User();
        return null;
    }

    public User getUserByLogin(String login) {
        return null;
    }
}
