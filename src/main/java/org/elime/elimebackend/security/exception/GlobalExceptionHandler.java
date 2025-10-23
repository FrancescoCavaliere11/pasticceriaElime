package org.elime.elimebackend.security.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.elime.elimebackend.data.dto.response.ErrorDto;
import org.elime.elimebackend.security.exception.customException.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    int i = 4321;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException e) {
        String errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ErrorDto(
                new Date(),
                errors,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleConstraintViolation(HttpServletRequest request, ConstraintViolationException e) {
        String errors = e.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));

        return new ErrorDto(
                new Date(),
                errors,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto onException(HttpServletRequest request, Exception e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto onEntityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto onValidatorException(HttpServletRequest request, TokenException e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto onTokenExpiredException(HttpServletRequest request, TokenExpiredException e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MissingEntityIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto onMissingEntityIdException(HttpServletRequest request, MissingEntityIdException e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MaxDailyOrderExceededException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto onMaxDailyOrderExceededException(HttpServletRequest request, MaxDailyOrderExceededException e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InvalidOrderDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto onInvalidOrderDateException(HttpServletRequest request, InvalidOrderDateException e) {
        return new ErrorDto(
                new Date(),
                e.getMessage(),
                request.getRequestURI()
        );
    }
}
