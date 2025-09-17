package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {
    public static Customer createCustomer(Address address, Contact contact, String firstName, String lastName, double customerDiscount, String userName, String password, String role) {
        if (!Helper.areAllObjectsNotNull(address, contact) || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(userName) || Helper.isNullOrEmpty(password) || Helper.isNullOrEmpty(role) || Helper.doubleIsInvalid(customerDiscount)) {
            return null;
        }
        return new Customer.Builder()
                .setAddress(address)
                .setContact(contact)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .setCustomerDiscount(customerDiscount)
                .build();
    }
}