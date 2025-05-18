package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class UserFactory {
    public static User createUser(int userId, int addressId, int contactId, String firstName, String lastName, String userName, String password, String role) {
        if (Helper.intIsNull(userId) || Helper.intIsNull(addressId) || Helper.intIsNull(contactId)
                || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)
                || Helper.isNullOrEmpty(userName) || Helper.isNullOrEmpty(password)
                || Helper.isNullOrEmpty(role)) {
            return null;
        }

        return new User.Builder()
                .setUserId(userId)
                .setAddressId(addressId)
                .setContactId(contactId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .build();
    }
}
