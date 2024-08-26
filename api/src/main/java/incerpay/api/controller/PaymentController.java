package incerpay.api.controller;

import incerpay.api.model.Payment;
import incerpay.api.model.PaymentRequest;
import incerpay.api.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*
    결제 요청 처리 API
     */
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.createPayment(paymentRequest.getAmount(), paymentRequest.getCurrency());

        return ResponseEntity.ok(payment);
    }
}