package za.ac.cput.services;

import za.ac.cput.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Payment createPayment(Payment payment);
    Optional<Payment> readPayment(int paymentId);
    Payment updatePayment(Payment payment);
    boolean deletePayment(int paymentId);
    List<Payment> getAllPayments();
}
