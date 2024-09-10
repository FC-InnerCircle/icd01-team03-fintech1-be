package incerpay.api.controller;

import incerpay.api.exception.UnauthorizedException;
import incerpay.api.model.CardListResponse;
import incerpay.api.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * 카드 목록 조회 (SDK -> 결제 BFF)
     * 이용 가능한 카드사 목록 조회 (ClientKey - 공개 키 이용)
     * @param clientKey
     * @return
     * @throws Exception
     */
    @Tag(name = "카드 리스트 확인", description = "카드사 정보 리스트 조회")
    @GetMapping("")
    public CardListResponse getCardList(@RequestHeader("Authorization") String clientKey) {

        // clientKey 검증
        if (!isValidClientKey(clientKey)) {
            throw new UnauthorizedException("클라이언트 키 오류");
        }

        // 가맹점 이용 가능 카드 리스트를 반환한다
        return cardService.getCardList(clientKey);
    }

    private boolean isValidClientKey(String clientKey) {

        // TODO member 서비스

        return true;
    }
}