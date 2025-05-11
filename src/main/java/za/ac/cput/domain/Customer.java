/*   Customer.java

     Customer POJO class

     Author: Zakhir McKinnon (22016299)

     Date: 11 May 2025 */

package za.ac.cput.domain;

public class Customer {
    protected String userId;
    protected double customerDiscount;

    private Customer() {
    }

    private Customer(Builder builder) {
        this.userId = builder.userId;
        this.customerDiscount = builder.customerDiscount;
    }

    public String getUserId() {
        return userId;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", customerDiscount=" + customerDiscount +
                '}';
    }

    public static class Builder {
        private String userId;
        private double customerDiscount;

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setCustomerDiscount(double customerDiscount) {
            this.customerDiscount = customerDiscount;
            return this;
        }

        public Builder copy(Customer customer) {
            this.userId = customer.userId;
            this.customerDiscount = customer.customerDiscount;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
