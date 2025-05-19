package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {
    public static Customer createCustomer(int userId, int addressId, int contactId, String firstName, String lastName,
                                          double customerDiscount, String userName, String password, String role) {
        if (Helper.intIsNull(userId) || Helper.intIsNull(addressId) || Helper.intIsNull(contactId)
                || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)
                || customerDiscount < 0) {
            return null;
        }

        return new Customer.Builder()
                .setUserId(userId)
                .setAddressId(addressId)
                .setContactId(contactId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .setCustomerDiscount(customerDiscount)
                .build();
    }
}
