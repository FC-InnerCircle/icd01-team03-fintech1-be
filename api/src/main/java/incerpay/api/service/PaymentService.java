package incerpay.api.service;

import incerpay.api.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    private Map<String, Payment> paymentMap = new HashMap<>();

    /*
    결제 요청 처리
     */
    public Payment createPayment(double amount, String currency) {

        String id = UUID.randomUUID().toString();
        Payment payment = new Payment(id, amount, currency, "pending", LocalDateTime.now());
        paymentMap.put(id, payment);

        return payment;
    }
}