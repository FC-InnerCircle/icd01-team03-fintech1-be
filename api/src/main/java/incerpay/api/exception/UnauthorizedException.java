package incerpay.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 403
 * API 키 오류 (만료 키, uuid 권한 없음)
 */
@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    private String errorCode;

    public UnauthorizedException(String message) {
        super(message);
        this.errorCode = String.valueOf(HttpStatus.FORBIDDEN);
    }
}
