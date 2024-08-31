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
    private String uuid;
    private String timestamp;

}
