package com.AsiaAutmation.JournalApp.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Getter
@RequiredArgsConstructor
public enum Exceptions {
    USER_NOT_FOUND(NOT_FOUND,"USER NOT FOUND","User does not exist", LocalDateTime.now()),
    NO_JOURNAL_ENTRIES_FOUND(NO_CONTENT,"JOURNAL ENTRIES NO CONTENT", "No journal entries present", LocalDateTime.now())
    ;
    private final HttpStatus status;
    private final String message;
    private final String description;
    private final LocalDateTime time;
}
