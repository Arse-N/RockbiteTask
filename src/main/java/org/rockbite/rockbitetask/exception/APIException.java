package org.rockbite.rockbitetask.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Builder
public class APIException {
    private String message;
    private HttpStatus httpStatus;
    private String error;
    private Timestamp timestamp;
}
