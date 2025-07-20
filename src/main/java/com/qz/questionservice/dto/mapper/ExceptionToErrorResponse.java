package com.qz.questionservice.dto.mapper;

import com.qz.questionservice.dto.ErrorResponse;

import java.time.LocalDateTime;

public class ExceptionToErrorResponse<T extends Exception> implements Mapper<T, ErrorResponse> {
    @Override
    public ErrorResponse map(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        return errorResponse;
    }
}
