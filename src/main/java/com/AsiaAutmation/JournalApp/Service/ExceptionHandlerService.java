package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Enums.Exceptions;
import com.AsiaAutmation.JournalApp.Exception.NoJournalEntriesFoundException;
import com.AsiaAutmation.JournalApp.Exception.UserNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerService {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> userNotFoundException(final UserNotFoundException userNotFoundException){
        log.warn("User Exception Occurred", userNotFoundException);
        return this.makeResponseEntity(userNotFoundException.getException());
    }

    @ExceptionHandler({NoJournalEntriesFoundException.class})
    public ResponseEntity<?> noJournalEntriesFound(final NoJournalEntriesFoundException noJournalEntriesFoundException){
        log.warn("No Journal Entries", noJournalEntriesFoundException);
        ResponseEntity<?> response = this.makeResponseEntity(noJournalEntriesFoundException.getException());
        return response;
    }

    private ResponseEntity<ErrorResponse> makeResponseEntity(final Exceptions exception){
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorResponse(exception.getStatus().name(),exception.getMessage(),exception.getDescription(),exception.getTime().toString()));
    }

    record ErrorResponse(String code, String message, String description, String time) {
    }
}
