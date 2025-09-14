package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.paymentMethod;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByPaymentStatus(String paymentStatus);
    List<Payment> findByPaymentMethod(paymentMethod paymentMethod);
}
