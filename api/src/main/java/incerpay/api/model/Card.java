package incerpay.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {

    /* 카드사 코드 */
    private String cardCd;
    /* 카드사명 */
    private String cardCompany;
    /* 무이자 할부 개월수 */
    private int installmentPeriod;

}
