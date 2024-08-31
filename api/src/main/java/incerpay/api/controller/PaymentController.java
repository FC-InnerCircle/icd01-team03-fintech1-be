package incerpay.api.controller;

import incerpay.api.exception.ValidationException;
import incerpay.api.model.Payment;
import incerpay.api.model.PaymentRequest;
import incerpay.api.model.PaymentResponse;
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

    /**
     * 결제 요청 (SDK -> 결제 BFF)
     * 클라이언트 키를 통한 요청 (공개 키)
     * @param clientKey
     * @param paymentRequest
     * @return
     */
    @PostMapping("/request")
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestHeader("Authorization") String clientKey,
            @RequestBody PaymentRequest paymentRequest) {

        // 카드 유효성 검사
        validatePaymentRequest(paymentRequest);

        // 결재 ID 채번 (카드 서비스)
        Payment payment = paymentService.createPayment(clientKey, paymentRequest);

        // TODO 카드사 API 호출

        // TODO 4조에서 Test Card 데이터 기반으로 결제 결과 응답

        // 백엔드 서버의 응답을 결제 응답으로 생성
        PaymentResponse paymentResponse = new PaymentResponse(
                payment.getId(),
                payment.getTimestamp()
        );

        // 수정된 응답을 클라이언트에게 반환
        // TODO 성공 시 리다이렉트 URL
        return ResponseEntity.ok(paymentResponse);
    }

    /**
     * 카드 유효성 검증
     * @param paymentRequest
     */
    private void validatePaymentRequest(PaymentRequest paymentRequest) {

        if (paymentRequest.getPrice() <= 0) {
            throw new ValidationException("INVALID_PRICE", "The payment price must be greater than zero.");
        }

        /* 카드번호 검증 */
        if (paymentRequest.getCardNumber() == null) {
            throw new ValidationException("INVALID_CARD_NUMBER", "The card number is required.");
        } else {
            paymentRequest.setCardNumber(paymentRequest.getCardNumber().replaceAll("[^0-9]", ""));
            if (paymentRequest.getCardNumber().length() != 16) {
                throw new ValidationException("INVALID_CARD_NUMBER", "The card number must be 16 digits.");
            }
        }

        // TODO 카드 본인인증 (카드사 API 호출)
        // TODO 테스트 카드 - 카드 유효성 검증 (4조)
    }
}