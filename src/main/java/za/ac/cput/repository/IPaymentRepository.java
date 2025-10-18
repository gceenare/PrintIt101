package za.ac.cput.repository;


import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByPaymentStatus(String paymentStatus);

    List<Payment> findByAmountGreaterThan(double amount);

    List<Payment> findByAmountLessThanEqual(double amount);

    List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);

    List<Payment> findByPaymentDate(String paymentDate);

    List<Payment> findByPaymentStatusAndPaymentMethod(String paymentStatus, PaymentMethod paymentMethod);
}
