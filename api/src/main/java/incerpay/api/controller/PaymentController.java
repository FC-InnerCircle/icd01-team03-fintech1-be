package incerpay.api.controller;

import incerpay.api.model.Payment;
import incerpay.api.model.PaymentRequest;
import incerpay.api.service.PaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<String> createPayment(
            @RequestHeader("Authorization") String apikey,
            @RequestBody PaymentRequest paymentRequest) {

        Payment payment = paymentService.createPayment(paymentRequest.getAmount(), paymentRequest.getCurrency());

        String backendUrl = "http://backend-server.com/api/payments";

        // 헤더에 Authorization 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apikey);

        // 백엔드 서버로 요청 전송
        HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);
        ResponseEntity<String> response = restTemplate.exchange(backendUrl, HttpMethod.POST, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}