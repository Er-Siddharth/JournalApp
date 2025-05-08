package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Enums.Exceptions;
import com.AsiaAutmation.JournalApp.Exception.InvalidArgumentException;
import com.AsiaAutmation.JournalApp.Exception.NoJournalEntriesFoundException;
import com.AsiaAutmation.JournalApp.Exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.http.HttpRequest;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerService {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class})
    public ErrorResponse userNotFoundException(final UserNotFoundException userNotFoundException){
        log.warn("User Exception Occurred", userNotFoundException);
        Exceptions ex = userNotFoundException.getException();
        return new ErrorResponse(ex.getMessage(), ex.getDescription(),ex.getTime());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoJournalEntriesFoundException.class)
    public ErrorResponse noJournalEntriesFound(final NoJournalEntriesFoundException noJournalEntriesFoundException){
        log.warn("No Journal Entries", noJournalEntriesFoundException);
        Exceptions ex = noJournalEntriesFoundException.getException();
        return new ErrorResponse(ex.getMessage(),ex.getDescription(),ex.getTime());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidArgumentException.class})
    public ErrorResponse invalidArgsException(final InvalidArgumentException invalidArgumentException){
        log.warn("Invalid arguments passed", invalidArgumentException);
        Exceptions ex = invalidArgumentException.getException();
        return new ErrorResponse(ex.getMessage(), ex.getDescription(), ex.getTime());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
            log.warn(String.format("%s : %s", fieldName,message));
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public ErrorResponse EntryDoesNotExist(InvalidParameterException exception){
        log.warn(exception.getMessage());
        return new ErrorResponse("No Such Entry", exception.getMessage(), LocalDateTime.now());
    }

//    private ResponseEntity<ErrorResponse> makeResponseEntity(final Exceptions exception){
//        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(),exception.getDescription(),exception.getTime()),HttpStatus.BAD_REQUEST );
//    }

    record ErrorResponse(String message, String description, LocalDateTime time) {
    }
}
