package incerpay.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String id;
    private int price;
    private String cardNumber;
    private String cvc;
    private String expireDate;
    private String cardCompany;
    private int installmentPeriod;
    private String customerId;
    private String paymentType;
    private String timestamp;
}
