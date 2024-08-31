package incerpay.api.service;

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

        // TODO 카드 서비스 API 를 호출하여 결재 키 채번

        String url = "/api/payments";
        Mono<Payment> paymentMono = webClient.post()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, apikey)  // Authorization 헤더를 백엔드 서버로 전달
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(Payment.class);
        Payment payment = paymentMono.block();
        paymentMap.put(payment.getId(), payment);

        return payment;
    }
}