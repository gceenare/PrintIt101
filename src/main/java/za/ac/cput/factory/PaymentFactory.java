package za.ac.cput.factory;

import za.ac.cput.domain.Payment;

import za.ac.cput.domain.PaymentMethod;
import java.time.LocalDate;

public class PaymentFactory {

    public static Payment createPayment(double amount, PaymentMethod paymentMethod, String paymentStatus) {
        if (amount <= 0 || paymentMethod == null) {
            return null;
        }
        return new Payment.Builder()
                .setAmount(amount)
                .setPaymentMethod(paymentMethod)
                .setPaymentDate(LocalDate.now().toString())
                .setPaymentStatus(paymentStatus == null ? "PENDING" : paymentStatus)
                .build();
    }

}
