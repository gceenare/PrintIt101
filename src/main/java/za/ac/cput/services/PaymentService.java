package za.ac.cput.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> readPayment(int paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        if (paymentRepository.existsById(payment.getPaymentId())) {
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public boolean deletePayment(int paymentId) {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
            return true;
        }
        return false;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
