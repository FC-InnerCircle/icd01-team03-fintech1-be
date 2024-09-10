package incerpay.api.model;

import lombok.*;

/**
 * 백엔드 서버에서 반환하는 결제 응답 데이터
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class PaymentResponse {

    /* 결제 고유 식별자 */
    private String uuid;
    /* 결제 요청 시간 */
    private String timestamp;

}
