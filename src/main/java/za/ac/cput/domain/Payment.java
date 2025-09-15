package za.ac.cput.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    private String paymentDate;

    private String paymentStatus;

    public Payment() { }

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDate = builder.paymentDate;
        this.paymentStatus = builder.paymentStatus;
    }

    // Getters
    public Integer getPaymentId() { return paymentId; }
    public double getAmount() { return amount; }
    public paymentMethod getPaymentMethod() { return paymentMethod; }
    public String getPaymentDate() { return paymentDate; }
    public String getPaymentStatus() { return paymentStatus; }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", paymentMethod=" + paymentMethod +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public static class Builder {
        private Integer paymentId;
        private double amount;
        private paymentMethod paymentMethod;
        private String paymentDate;
        private String paymentStatus;

        public Builder setPaymentId(Integer paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }
        public Builder setPaymentMethod(paymentMethod paymentMethod) {
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
