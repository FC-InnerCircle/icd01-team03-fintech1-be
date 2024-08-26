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
    private double amount;
    private String currency;
    private String status;
    private LocalDateTime createdAt;

}
