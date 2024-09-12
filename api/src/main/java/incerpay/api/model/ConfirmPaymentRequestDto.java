package incerpay.api.model;

import lombok.Data;

@Data
public class ConfirmPaymentRequestDto {

    /* 결제 고유 식별자 */
    private String uuid;

}
