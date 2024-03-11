package br.com.vinicius.basiccrud.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomizedExceptionsHandler {

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedDataException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
