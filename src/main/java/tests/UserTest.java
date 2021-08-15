package tests;

import junit.framework.TestCase;
import model.User;
import model.UserDAO;

public class UserTest extends TestCase {

    private User user;

    @Override
    protected void setUp() throws Exception {
        String password = String.valueOf("doe".hashCode());
        user = new User(1, "John", password, "black", false);
    }

    public void testUserId() {
        assertEquals(user.getId(), 1);

        user.setId(2);
        assertEquals(user.getId(), 2);

        user.setId(1);
        assertEquals(user.getId(), 1);

    }

    public void testUsername() {
        assertEquals(user.getUsername(), "John");
        user.setUsername("Jane");
        assertEquals(user.getUsername(), "Jane");
        user.setUsername("John");
        assertEquals(user.getUsername(), "Jane");
    }

    public void testUserPassword() {
        String password = String.valueOf("doe".hashCode());
        assertEquals(user.getPassword(), password);

        password = String.valueOf("doe2".hashCode());
        user.setPassword(password);
        assertEquals(user.getPassword(), password);
    }

    public void testUserAvatar() {
        assertEquals(user.getAvatar(), "black");

        user.setAvatar("purple");
        assertEquals(user.getAvatar(), "purple");

        user.setAvatar("black");
        assertEquals(user.getAvatar(), "black");
    }

    public void testUserIsAdmin() {
        assertFalse(user.getAdmin());

        user.setAdmin(true);
        assertTrue(user.getAdmin());

        user.setAdmin(false);
        assertFalse(user.getAdmin());

    }

    public void testUserExists() {
        assertTrue(UserDAO.usernameExists("admin"));
        assertFalse(UserDAO.usernameExists(""));
    }

    public void testUserAdmin() {
        assertTrue(UserDAO.userIsAdmin("admin"));
        UserDAO.getIdByUsername("admin");
    }

    public void testLogin() {
        String password = String.valueOf("admin".hashCode());
        assertTrue(UserDAO.successLogin("admin", password));
        assertFalse(UserDAO.successLogin("john", "doe"));
    }

}
