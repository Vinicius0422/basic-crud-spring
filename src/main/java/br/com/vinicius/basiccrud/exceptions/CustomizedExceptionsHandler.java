package br.com.vinicius.basiccrud.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomizedExceptionsHandler {

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedDataException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        errors
                        , request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionResponse> handleDateTimeParseException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(Exception ex, HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(Exception ex,  HttpServletRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
