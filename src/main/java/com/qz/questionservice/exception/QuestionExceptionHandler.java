package com.qz.questionservice.exception;

import com.qz.questionservice.dto.ErrorResponse;
import com.qz.questionservice.dto.mapper.ExceptionToErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuestionExceptionHandler {

    @ExceptionHandler(QuestionNotFound.class)
    public ResponseEntity<ErrorResponse> questionNotFound(QuestionNotFound exception, HttpServletRequest request) {
        ExceptionToErrorResponse<QuestionNotFound> mapper = new ExceptionToErrorResponse<>();
        ErrorResponse errorResponse = mapper.map(exception);
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
