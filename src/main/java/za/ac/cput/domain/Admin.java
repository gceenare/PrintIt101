/* Admin.java
   Admin POJO class - extends User for admin functionality
   Author: System Generated
   Date: 16 September 2025
*/

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {


    @Column(name = "admin_level", nullable = false)
    private String adminLevel; // e.g., "SUPER_ADMIN", "ADMIN", "MODERATOR"

    @Column(name = "department", nullable = false)
    private String department; // e.g., "CUSTOMER_SERVICE", "OPERATIONS", "IT"

    @Column(name = "permissions", length = 1000)
    private String permissions; // JSON string or comma-separated permissions

    protected Admin() {}

    private Admin(Builder builder) {
        super(builder);
        this.adminLevel = builder.adminLevel;
        this.department = builder.department;
        this.permissions = builder.permissions;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public String getDepartment() {
        return department;
    }

    public String getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminLevel='" + adminLevel + '\'' +
                ", department='" + department + '\'' +
                ", permissions='" + permissions + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(userId, admin.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public static class Builder extends User.Builder<Builder> {
        private String adminLevel;
        private String department;
        private String permissions;

        public Builder setAdminLevel(String adminLevel) {
            this.adminLevel = adminLevel;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setPermissions(String permissions) {
            this.permissions = permissions;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Builder copy(User user) {
            super.copy(user);
            if (user instanceof Admin admin) {
                this.adminLevel = admin.adminLevel;
                this.department = admin.department;
                this.permissions = admin.permissions;
            }
            return this;
        }

        @Override
        public Admin build() {
            return new Admin(this);
        }
    }
}