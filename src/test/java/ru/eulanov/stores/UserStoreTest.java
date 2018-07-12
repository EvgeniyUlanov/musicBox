package ru.eulanov.stores;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.eulanov.InitDataBase;
import ru.eulanov.entities.User;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UserStoreTest {

    private UserStore userStore;
    private User userPavel;
    private User userMike;

    @BeforeClass
    public static void initDb() {
        InitDataBase.initDB();
    }

    @Before
    public void start() {
        userStore = UserStore.getInstance();
        userPavel = new User();
        userPavel.setName("pavel");
        userPavel.setLogin("login");
        userPavel.setPassword("1");
        userPavel.setAddress("address");
        userPavel.setRole("user");
        userMike = new User();
        userMike.setName("mike");
        userMike.setLogin("log");
        userMike.setPassword("1");
        userMike.setAddress("moscow");
        userMike.setRole("admin");
    }

    @After
    public void cleanTableUsers() {
        InitDataBase.createTableUsers();
        InitDataBase.createTableMusicPreferes();
    }

    @Test
    public void testAddGetDeleteUser() {
        userStore.addUser(userPavel);
        List<User> userList = userStore.getAllUsers();
        assertTrue(userList.contains(userPavel));

        userPavel = userList.get(userList.indexOf(userPavel));

        userStore.deleteUser(userPavel.getId());

        userList = userStore.getAllUsers();

        assertTrue(!userList.contains(userPavel));
    }

    @Test
    public void testSetGetDeleteFavoriteMusic() {
        userStore.addUser(userPavel);
        List<User> userList = userStore.getAllUsers();
        for (User u : userList) {
            if (u.equals(userPavel)) {
                userPavel.setId(u.getId());
            }
        }

        assertTrue(userList.contains(userPavel));

        userStore.setMusicPrefToUser(userPavel.getId(), "rock");
        userPavel = userStore.getUserById(userPavel.getId());

        assertTrue(userPavel.getFavoriteMusic().contains("rock"));

        userStore.deleteMusicFromUser(userPavel.getId(), "rock");
        userPavel = userStore.getUserById(userPavel.getId());

        assertTrue(!userPavel.getFavoriteMusic().contains("rock"));

        userStore.deleteUser(userPavel.getId());
    }

    @Test
    public void testUpdateUser() {
        userStore.addUser(userPavel);
        List<User> userList = userStore.getAllUsers();
        for (User u : userList) {
            if (u.equals(userPavel)) {
                userPavel.setId(u.getId());
            }
        }

        assertTrue(userList.contains(userPavel));

        userPavel.setName("nikovay");
        userStore.updateUser(userPavel);
        userPavel = userStore.getUserById(userPavel.getId());

        assertTrue(userPavel.getName().equals("nikovay"));

        userStore.deleteUser(userPavel.getId());
    }

    @Test
    public void testFindUserByName() {
        userStore.addUser(userPavel);
        userStore.addUser(userMike);
        List<User> userList = userStore.findUserByName("pavel");

        assertTrue(userList.contains(userPavel));
        assertTrue(!userList.contains(userMike));
    }

    @Test
    public void testFindUserByLogin() {
        userStore.addUser(userPavel);
        userStore.addUser(userMike);
        List<User> userList = userStore.findUserByLogin("login");

        assertTrue(userList.contains(userPavel));
        assertTrue(!userList.contains(userMike));
    }

    @Test
    public void testFindUserByRole() {
        userStore.addUser(userPavel);
        userStore.addUser(userMike);
        List<User> userList = userStore.findUserByRole("admin");

        assertTrue(!userList.contains(userPavel));
        assertTrue(userList.contains(userMike));
    }

    @Test
    public void testFindUserByAddress() {
        userStore.addUser(userPavel);
        userStore.addUser(userMike);
        List<User> userList = userStore.findUserByAddress("moscow");

        assertTrue(!userList.contains(userPavel));
        assertTrue(userList.contains(userMike));
    }

    @Test
    public void testFindUserByMusic() {
        userStore.addUser(userPavel);
        userStore.addUser(userMike);
        List<User> userList = userStore.getAllUsers();
        userPavel.setId(userList.get(userList.indexOf(userPavel)).getId());
        userMike.setId(userList.get(userList.indexOf(userMike)).getId());

        userStore.setMusicPrefToUser(userPavel.getId(), "rock");
        userStore.setMusicPrefToUser(userMike.getId(), "electro");

        userList = userStore.findUserByMusic("rock");
        assertTrue(userList.contains(userPavel));
        assertTrue(!userList.contains(userMike));

        userStore.setMusicPrefToUser(userMike.getId(), "rock");
        userList = userStore.findUserByMusic("rock");

        assertTrue(userList.contains(userPavel));
        assertTrue(userList.contains(userMike));
    }
}