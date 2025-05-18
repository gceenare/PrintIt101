package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    // ------------------ Valid User ------------------
    @Test
    public void testCreateValidUser() {
        User user = UserFactory.createUser(
                1, 100, 200,
                "Thabiso", "Mbatha",
                "thabisoM", "password123", "Admin"
        );
        assertNotNull(user);
        System.out.println("Valid User:\n" + user);
    }

    // ------------------ Invalid: Null userId ------------------
    @Test
    public void testCreateUserWithNullUserId() {
        User user = UserFactory.createUser(
                0, 100, 200,
                "Thabiso", "Mbatha",
                "thabisoM", "password123", "Admin"
        );
        assertNull(user);
    }

    // ------------------ Invalid: Empty First Name ------------------
    @Test
    public void testCreateUserWithEmptyFirstName() {
        User user = UserFactory.createUser(
                2, 100, 200,
                "", "Mbatha",
                "thabisoM", "password123", "Admin"
        );
        assertNull(user);
    }

    // ------------------ Invalid: Empty Password ------------------
    @Test
    public void testCreateUserWithEmptyPassword() {
        User user = UserFactory.createUser(
                3, 100, 200,
                "Thabiso", "Mbatha",
                "thabisoM", "", "Admin"
        );
        assertNull(user);
    }

    // ------------------ Invalid: All Fields Invalid ------------------
    @Test
    public void testCreateCompletelyInvalidUser() {
        User user = UserFactory.createUser(
                0, 0, 0,
                "", "", "", "", ""
        );
        assertNull(user);
    }
}