
package za.ac.cput.factory;

import za.ac.cput.domain.Employee;
import za.ac.cput.util.Helper;

public class EmployeeFactory {
    public static Employee createEmployee(int userId, int addressId, int contactId, String firstName, String lastName,
                                          String position, double staffDiscount,String userName, String password, String role) {
        if (Helper.intIsNull(addressId) || Helper.intIsNull(contactId)
                || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)
                || Helper.isNullOrEmpty(position) || staffDiscount < 0) {
            return null;
        }

        return new Employee.Builder()
                .setUserId(userId)
                .setAddressId(addressId)
                .setContactId(contactId)
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
