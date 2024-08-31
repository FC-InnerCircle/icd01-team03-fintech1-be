package incerpay.api.model;

import lombok.*;

/**
 * 클라이언트가 보내는 결제 요청 데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {

    private int price;
    private String cardNumber;
    private String cvc;
    private String expireDate;
    private String cardCompany;
    private int installmentPeriod;
    private String customerId;
    private String paymentType;

}
