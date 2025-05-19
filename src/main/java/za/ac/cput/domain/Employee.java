package za.ac.cput.domain;

public class Employee extends User {
    protected int employeeId;
    protected double staffDiscount;
    protected String position;

    private Employee() {
    }

    private Employee(Builder builder) {
        super(builder);
        this.employeeId = builder.employeeId;
        this.staffDiscount = builder.staffDiscount;
        this.position = builder.position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getStaffDiscount() {
        return staffDiscount;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", staffDiscount=" + staffDiscount +
                ", position='" + position + '\'' +
                "} " + super.toString();
    }

    public static class Builder extends User.Builder {
        private int employeeId;
        private double staffDiscount;
        private String position;

        public Builder setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder setStaffDiscount(double staffDiscount) {
            this.staffDiscount = staffDiscount;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        @Override
        public Builder setUserId(int userId) {
            super.setUserId(userId);
            return this;
        }

        @Override
        public Builder setAddressId(int addressId) {
            super.setAddressId(addressId);
            return this;
        }

        @Override
        public Builder setContactId(int contactId) {
            super.setContactId(contactId);
            return this;
        }

        @Override
        public Builder setFirstName(String firstName) {
            super.setFirstName(firstName);
            return this;
        }

        @Override
        public Builder setLastName(String lastName) {
            super.setLastName(lastName);
            return this;
        }

        @Override
        public Builder setUserName(String userName) {
            super.setUserName(userName);
            return this;
        }

        @Override
        public Builder setPassword(String password) {
            super.setPassword(password);
            return this;
        }

        @Override
        public Builder setRole(String role) {
            super.setRole(role);
            return this;
        }

        @Override
        public Builder copy(User user) {
            super.copy(user);
            if (user instanceof Employee employee) {
                this.employeeId = employee.employeeId;
                this.staffDiscount = employee.staffDiscount;
                this.position = employee.position;
            }
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(this);
        }
    }
}
