/* AdminFactory.java
   Admin Factory class
   Author: System Generated
   Date: 16 September 2025
*/

package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;

public class AdminFactory {

    public static Admin createAdmin(Address address, Contact contact,
                                    String firstName, String lastName,
                                    String adminLevel, String department,
                                    String permissions, String userName,
                                    String password, String role) {
        if (!Helper.areAllObjectsNotNull(address, contact)
                || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)
                || Helper.isNullOrEmpty(adminLevel) || Helper.isNullOrEmpty(department)
                || Helper.isNullOrEmpty(userName) || Helper.isNullOrEmpty(password)
                || Helper.isNullOrEmpty(role)) {
            return null;
        }

        return new Admin.Builder()
                .setAddress(address)
                .setContact(contact)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .setAdminLevel(adminLevel)
                .setDepartment(department)
                .setPermissions(permissions)
                .build();
    }

    public static Admin createSuperAdmin(Address address, Contact contact,
                                         String firstName, String lastName,
                                         String userName, String password) {
        return createAdmin(address, contact, firstName, lastName,
                "SUPER_ADMIN", "OPERATIONS",
                "ALL_PERMISSIONS", userName, password, "ADMIN");
    }

    public static Admin createCustomerServiceAdmin(Address address, Contact contact,
                                                   String firstName, String lastName,
                                                   String userName, String password) {
        return createAdmin(address, contact, firstName, lastName,
                "ADMIN", "CUSTOMER_SERVICE",
                "MANAGE_CUSTOMERS,VIEW_ORDERS,UPDATE_ORDERS",
                userName, password, "ADMIN");
    }
}