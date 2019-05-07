package cn.waynezw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Permission denied exception
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(String message) {
        super(message);
    }

    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    protected PermissionDeniedException() {
        super();
    }
}