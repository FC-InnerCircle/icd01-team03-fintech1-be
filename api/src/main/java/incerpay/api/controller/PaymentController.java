package incerpay.api.controller;

import incerpay.api.exception.ValidationException;
import incerpay.api.model.Payment;
import incerpay.api.model.PaymentRequest;
import incerpay.api.model.PaymentResponse;
import incerpay.api.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 결제 요청 (SDK -> 결제 BFF)
     * 클라이언트 키를 통한 요청 (공개 키)
     * @param clientKey
     * @param paymentRequest
     * @return
     */
    @Tag(name = "결제 요청")
    @PostMapping("/request")
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestHeader("Authorization") String clientKey,
            @RequestBody PaymentRequest paymentRequest) {

        // TODO forward to pay-gate
        Payment payment = paymentService.createPayment(clientKey, paymentRequest);

        // 백엔드 서버의 응답을 결제 응답으로 생성
        PaymentResponse paymentResponse = new PaymentResponse(
                payment.getId(),
                payment.getTimestamp()
        );

        // 수정된 응답을 클라이언트에게 반환
        // TODO 성공 시 리다이렉트 URL
        return ResponseEntity.ok(paymentResponse);
    }
}