package za.ac.cput.factory;

import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;

public class EmployeeFactory {
    public static Employee createEmployee(Address address, Contact contact,
                                          String firstName, String lastName,
                                          String position, double staffDiscount,
                                          String userName, String password, String role) {
        if (!Helper.areAllObjectsNotNull(address, contact)
                || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)
                || Helper.isNullOrEmpty(position) || Helper.isNullOrEmpty(userName)
                || Helper.isNullOrEmpty(password) || Helper.isNullOrEmpty(role)
                || Helper.doubleIsInvalid(staffDiscount)) {
            return null;
        }

        return new Employee.Builder()
                .setAddress(address)
                .setContact(contact)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .setStaffDiscount(staffDiscount)
                .setPosition(position)
                .build();
    }
}
