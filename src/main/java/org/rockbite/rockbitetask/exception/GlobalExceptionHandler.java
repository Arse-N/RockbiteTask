package org.rockbite.rockbitetask.exception;

import lombok.extern.slf4j.Slf4j;
import org.rockbite.rockbitetask.exception.custom.BadRequestException;
import org.rockbite.rockbitetask.exception.custom.ConflictException;
import org.rockbite.rockbitetask.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<APIException> handleException(Exception e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        APIException response = APIException.builder()
                .message(e.getLocalizedMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .error("Internal Server Error")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<APIException> handleValidationException(MethodArgumentNotValidException exception) {
        log.error("An error occurred: {}", exception.getMessage(), exception);
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String msg = fieldErrors.stream()
                .map(cur -> cur.getField() + " " + cur.getDefaultMessage())
                .collect(Collectors.joining(", "));

        APIException response = APIException.builder()
                .message(msg)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error("Bad Request")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<APIException> handleException400(BadRequestException e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        APIException response = APIException.builder()
                .message(e.getLocalizedMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error("Bad Request")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<APIException> handleException404(NotFoundException e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        APIException response = APIException.builder()
                .message(e.getLocalizedMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .error("Not Found")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<APIException> handleException409(ConflictException e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        APIException response = APIException.builder()
                .message(e.getLocalizedMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .error("Conflict")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
