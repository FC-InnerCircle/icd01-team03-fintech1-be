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

    /* 결제 금액 */
    private int price;
    /* 카드번호 */
    private String cardNumber;
    /* CVC 코드 */
    private String cvc;
    /* 유효기간 (MM/YY) */
    private String expireDate;
    /* 카드사 코드 */
    private String cardCd;
    /* 할부 기간 ( 0: 일시불 ) */
    private int installmentPeriod;
    /* 고객 생성 식별자 */
    private String customerId;
    /* 결제 종류 (CARD) */
    private String paymentType;

    /* 성공 url */
    private String successUrl;
    /* 실패 url */
    private String failUrl;
}
