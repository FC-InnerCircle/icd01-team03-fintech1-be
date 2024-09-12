package incerpay.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Payment {

    /* 걸제 ID = 주문번호 */
    private String paymentId;
    /* 거래 ID */
    private String transactionId;
    /* 상태 */
    private String status;

    private String timestamp;
}
