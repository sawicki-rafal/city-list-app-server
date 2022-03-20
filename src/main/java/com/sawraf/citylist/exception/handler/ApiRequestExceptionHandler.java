package com.sawraf.citylist.exception.handler;

import com.sawraf.citylist.exception.ApplicationException;
import com.sawraf.citylist.exception.message.MessageResolver;
import com.sawraf.citylist.exception.response.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.sawraf.citylist.exception.message.MessageCode.ERROR_UNKNOWN;
import static com.sawraf.citylist.exception.message.MessageCode.ERROR_VALIDATION;

@RestControllerAdvice
public class ApiRequestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageResolver messageResolver;
    @Value("${citylistapp.error.print.stacktrace:false}")
    private boolean printStackTrace;

    public ApiRequestExceptionHandler(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @ExceptionHandler({ApplicationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(ApplicationException exception) {
        final String message = messageResolver.getMessage(exception);
        final ErrorResponse errorResponse = buildErrorResponse(exception, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(Exception exception,
                                             String message) {
        final ErrorResponse errorResponse = new ErrorResponse(message);
        if (printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return errorResponse;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final String message = messageResolver.getMessage(ERROR_VALIDATION);
        final ErrorResponse errorResponse = buildErrorResponse(exception, message);
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorResponse.addValidationError(fieldError.getField(),
                        fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception) {
        final String message = messageResolver.getMessage(ERROR_UNKNOWN);
        final ErrorResponse errorResponse = buildErrorResponse(exception, message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
