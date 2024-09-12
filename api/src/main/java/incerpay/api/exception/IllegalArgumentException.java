package incerpay.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException {

    private String errorCode;

    public IllegalArgumentException(String message) {
        super(message);
        this.errorCode = String.valueOf(HttpStatus.NOT_FOUND);
    }
}
