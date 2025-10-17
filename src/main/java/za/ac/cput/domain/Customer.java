package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User {
    @Column(name = "customer_discount", nullable = false)
    private double customerDiscount;

    protected Customer() {
        super(); // This ensures proper initialization of the User part
    }

    private Customer(Builder builder) {
        super(builder); // This ensures proper ID handling from User class
        this.customerDiscount = builder.customerDiscount;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Double.compare(customer.customerDiscount, customerDiscount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerDiscount);
    }

    public static class Builder extends User.Builder<Builder> {
        private double customerDiscount;

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setCustomerDiscount(double customerDiscount) {
            this.customerDiscount = customerDiscount;
            return this;
        }

        @Override
        public Customer build() {
            return new Customer(this);
        }
    }
}
