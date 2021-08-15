package model;

import org.junit.*;


public class UserTest {

    private static User user;
    private static int id;

    @BeforeClass
    public static void setUp() throws Exception {
        String password = String.valueOf("password".hashCode());
        user = new User(1, "JohnDoe", password, "black", false);

        id = UserDAO.addUser(user);
        user.setId(id);
    }

    @Test
    public void testUserId() {
        Assert.assertEquals(user.getId(), id);

        user.setId(2);
        Assert.assertEquals(user.getId(), 2);

        user.setId(id);
        Assert.assertEquals(user.getId(), id);

        Assert.assertTrue(UserDAO.usernameExists(user.getUsername()));
        Assert.assertEquals(UserDAO.getIdByUsername(user.getUsername()), user.getId());
    }
    @Test
    public void testUsername() {
        Assert.assertEquals(user.getUsername(), "JohnDoe");

        user.setUsername("JaneDoe");
        Assert.assertEquals(user.getUsername(), "JaneDoe");

        user.setUsername("JohnDoe");
        Assert.assertEquals(user.getUsername(), "JohnDoe");
    }
    @Test
    public void testUserPassword() {
        String password = String.valueOf("password".hashCode());
        Assert.assertEquals(user.getPassword(), password);

        String other = String.valueOf("other".hashCode());
        user.setPassword(other);
        Assert.assertEquals(user.getPassword(), other);

        user.setPassword(password);
        Assert.assertEquals(user.getPassword(), user.getPassword());

        Assert.assertTrue(UserDAO.successLogin(user.getUsername(), user.getPassword()));
        Assert.assertFalse(UserDAO.successLogin(user.getUsername(), other));
    }
    @Test
    public void testUserAvatar() {
        Assert.assertEquals(user.getAvatar(), "black");

        user.setAvatar("purple");
        Assert.assertEquals(user.getAvatar(), "purple");

        user.setAvatar("black");
        Assert.assertEquals(user.getAvatar(), "black");
    }
    @Test
    public void testUserIsAdmin() {
        Assert.assertFalse(user.getAdmin());

        user.setAdmin(true);
        Assert.assertTrue(user.getAdmin());

        user.setAdmin(false);
        Assert.assertFalse(user.getAdmin());

        Assert.assertFalse(UserDAO.userIsAdmin(user.getUsername()));
        UserDAO.updateUserStatus(user.getUsername(), true);
        Assert.assertTrue(UserDAO.userIsAdmin(user.getUsername()));
        UserDAO.updateUserStatus(user.getUsername(), false);
        Assert.assertFalse(UserDAO.userIsAdmin(user.getUsername()));
    }

    @Test
    public void testDaoGetters() {
        if (UserDAO.userExists(1)) UserDAO.getUserById(1);
        UserDAO.getUserByAdmin(true);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        UserDAO.deleteUser(id);
    }
}
