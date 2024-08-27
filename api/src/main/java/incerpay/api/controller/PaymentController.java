package incerpay.api.controller;

import incerpay.api.model.Payment;
import incerpay.api.model.PaymentRequest;
import incerpay.api.model.PaymentResponse;
import incerpay.api.service.PaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    public PaymentController(PaymentService paymentService, RestTemplate restTemplate) {
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    /*
    결제 요청 처리 API
    - 백엔드 서버로 전달
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestHeader("Authorization") String apikey,
            @RequestBody PaymentRequest paymentRequest) {

        // 실제 결제를 처리하는 백엔드 서버 URL
        String backendUrl = "http://localhost:9000/";

        // 결제 요청 UUID 및 타임스탬프 생성
        String uuid = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();

        Payment payment = new Payment(
                uuid,
                paymentRequest.getPrice(),
                paymentRequest.getCardNumber(),
                paymentRequest.getCvc(),
                paymentRequest.getExpireDate(),
                paymentRequest.getCardCompany(),
                paymentRequest.getInstallmentPeriod(),
                paymentRequest.getCustomerId(),
                paymentRequest.getPaymentType(),
                timestamp.toString()
        );

        // 헤더에 Authorization 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apikey);

        // 결제 요청을 백엔드 서버로 전달
        HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);
        ResponseEntity<String> response = restTemplate.exchange(backendUrl, HttpMethod.POST, entity, String.class);

        // 백엔드 서버의 응답을 결제 응답으로 생성
        PaymentResponse paymentResponse = new PaymentResponse(uuid, timestamp.toString());

        // 결제 응답을 클라이언트로 반환
        return ResponseEntity.ok(paymentResponse);
    }
}