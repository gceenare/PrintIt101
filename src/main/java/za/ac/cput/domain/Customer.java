/*   Customer.java

     Customer POJO class

     Author: Zakhir McKinnon (22016299)

     Date: 11 May 2025 */

package za.ac.cput.domain;

public class Customer extends User {
    protected double customerDiscount;

    private Customer() {
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

        public Builder copy(Customer customer) {
            super.copy(customer);
            this.customerDiscount = customer.customerDiscount;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
