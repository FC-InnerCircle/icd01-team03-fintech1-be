package incerpay.api.model;

import lombok.*;

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
