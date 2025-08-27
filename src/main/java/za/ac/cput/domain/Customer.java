package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer extends User {
    private double customerDiscount;

    protected Customer() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(userId, customer.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public static class Builder extends User.Builder<Builder> {
        private double customerDiscount;

        public Builder setCustomerDiscount(double customerDiscount) {
            this.customerDiscount = customerDiscount;
            return this;
        }

        @Override
        protected Builder self() {
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
