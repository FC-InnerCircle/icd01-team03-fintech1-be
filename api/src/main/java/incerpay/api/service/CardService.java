package incerpay.api.service;

import incerpay.api.model.CardListResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CardService {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${seller.service.url}")
    private String sellerServiceUrl;

    public CardService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        System.out.println("상점 서비스 URL: " + sellerServiceUrl);
        this.webClient = webClientBuilder.baseUrl(sellerServiceUrl).build();
    }

    public CardListResponse getCardList(String apikey) {

        // TODO 4조에서 가져오기

        // 상점 서비스 API 를 호출하여 카드 관련 커스텀 데이터를 요청한다
        String url = "/card-list";
        Mono<CardListResponse> cardListResponseMono = webClient.get()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, apikey)  // Authorization 헤더를 백엔드 서버로 전달
                .retrieve()
                .bodyToMono(CardListResponse.class);

        return cardListResponseMono.block();
    }
}