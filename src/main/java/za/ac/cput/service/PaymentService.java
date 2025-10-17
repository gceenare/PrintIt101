package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;


import za.ac.cput.repository.PaymentRepository;

import java.util.List;

@Service
public  class PaymentService implements IPaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(Payment payment) {
        if (payment == null) return null;
        return repository.save(payment);
    }

    @Override
    public Payment read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Payment update(Payment payment) {
        if (payment == null || payment.getPaymentId() == null || !repository.existsById(payment.getPaymentId())) {
            return null;
        }
        return repository.save(payment);
    }

    @Override
    public boolean delete(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Payment> findByPaymentStatus(String status) {
        return repository.findByPaymentStatus(status);
    }

    @Override
    public List<Payment> findByPaymentMethod(PaymentMethod method) {
        return repository.findByPaymentMethod(method);
    }
}
