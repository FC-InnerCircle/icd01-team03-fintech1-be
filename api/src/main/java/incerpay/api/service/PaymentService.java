package incerpay.api.service;

import incerpay.api.exception.IllegalArgumentException;
import incerpay.api.exception.IllegalStateException;
import incerpay.api.model.Payment;
import incerpay.api.model.PaymentRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${payment.service.url}")
    private String paymentServiceUrl = "";

    private Map<String, Payment> paymentMap = new HashMap<>();

    public PaymentService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
        this.webClient = webClientBuilder.baseUrl(paymentServiceUrl).build();
    }

    @PostConstruct
    public void init() {
        System.out.println("카드 서비스 URL: " + paymentServiceUrl);
        this.webClient = webClientBuilder.baseUrl(paymentServiceUrl).build();
    }

    /*
    결제 요청 처리
     */
    public Payment createPayment(String apikey, PaymentRequest paymentRequest) {

        /* pay-gate */
        // 카드 유효성 검사
        // 결재 ID 채번 (카드 서비스)
        // 카드사 API 호출
        // 4조에서 Test Card 데이터 기반으로 결제 결과 응답

        String url = "/payment";
        Mono<Payment> paymentMono = webClient.post()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, apikey)  // Authorization 헤더를 백엔드 서버로 전달
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(Payment.class);
        Payment payment = paymentMono.block();
        paymentMap.put(payment.getPaymentId(), payment);

        return payment;
    }

    /*
    결제 취소
     */
    public Payment cancel(String paymentId, String transactionId) {

        // TODO 결제 조회
        Payment payment = this.createPayment("temp", null);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }

        if (!"APPROVED".equals(payment.getStatus())) {
            throw new IllegalStateException("");
        }

        // TODO 취소 요청을 전송

        return payment;
    }

    /* 결제 승인 */
    public Payment confirm(String paymentId, String transactionId) {

        // TODO 결제 조회
        Payment payment = this.createPayment("temp", null);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }

        if (!"PENDING".equals(payment.getStatus())) {
            throw new IllegalStateException("");
        }

        // TODO 승인 요청 전송

        return payment;
    }
}