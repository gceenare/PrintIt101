package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.paymentMethod;

public class PaymentFactory {


    public static Payment createPayment(int paymentId, double amount, paymentMethod payment, String paymentDate, String paymentStatus) {
        if (amount <= 0 || payment == null || paymentId <= 0) {
            return null;
        }
        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setAmount(amount)
                .setPaymentMethod(payment)
                .setPaymentDate(java.time.LocalDate.now().toString())
                .setPaymentStatus(paymentStatus)
                .build();
    }
}
