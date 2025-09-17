package za.ac.cput.service;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.paymentMethod;

import java.util.List;

public interface IPaymentService extends IService<Payment, Integer> {
    List<Payment> getAll();
    List<Payment> findByPaymentStatus(String status);
    List<Payment> findByPaymentMethod(paymentMethod method);
}
