package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends User {
    protected double customerDiscount;

    protected Customer() {
    }

    private Customer(Builder builder) {
        super(builder);
        this.customerDiscount = builder.customerDiscount;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerDiscount=" + customerDiscount +
                "} " + super.toString();
    }

    public static class Builder extends User.Builder {
        private double customerDiscount;

        public Builder setCustomerDiscount(double customerDiscount) {
            this.customerDiscount = customerDiscount;
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
            if (user instanceof Customer customer) {
                this.customerDiscount = customer.customerDiscount;
            }
            return this;
        }

        @Override
        public Customer build() {
            return new Customer(this);
        }
    }
}
