package com.AsiaAutmation.JournalApp.Exception;

import com.AsiaAutmation.JournalApp.Enums.Exceptions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private final Exceptions exception;
}
