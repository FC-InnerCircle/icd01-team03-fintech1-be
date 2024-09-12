package incerpay.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalStateException extends RuntimeException {

    private String errorCode;

    public IllegalStateException(String message) {
        super(message);
        this.errorCode = String.valueOf(HttpStatus.BAD_REQUEST);;
    }
}
