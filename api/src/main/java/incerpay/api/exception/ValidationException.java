package incerpay.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 400
 * 유효성 검사 실패 (금액 / 통화 / 카드번호 / API 키 / 파라미터 / 필수값 누락 등)
 */
@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private String errorCode;

    public ValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
