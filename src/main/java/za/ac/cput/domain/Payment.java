package za.ac.cput.domain;

/* Paymeny.java

     Payment POJO class

     Author: G Mbabe (222836040)

     Date: 11 May 2025 */

public class Payment {

    protected int paymentId;
    protected double amount;
    protected PaymentMethod paymentMethod;
    protected String paymentDate;
    private String paymentStatus;

    private Payment() {
    }
    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDate = builder.paymentDate;
        this.paymentStatus = builder.paymentStatus;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public double getAmount() {
        return amount;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", PaymentMethod=" + paymentMethod +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public static class Builder {
        private int paymentId;
        private double amount;
        private PaymentMethod paymentMethod;
        private String paymentDate;
        private String paymentStatus;

        public Builder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }
        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        public Builder setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }
        public Builder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }
        public Builder copy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.amount = payment.amount;
            this.paymentMethod = payment.paymentMethod;
            this.paymentDate = payment.paymentDate;
            this.paymentStatus = payment.paymentStatus;
            return this;
        }
        public Payment build() {
            return new Payment(this);
        }
    }
}