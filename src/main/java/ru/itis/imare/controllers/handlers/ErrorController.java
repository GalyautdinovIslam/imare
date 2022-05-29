package ru.itis.imare.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.imare.dto.responses.validation.ValidationExceptionDto;
import ru.itis.imare.dto.responses.validation.ValidationExceptionResponse;
import ru.itis.imare.exceptions.ForbiddenRequestException;
import ru.itis.imare.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ForbiddenRequestException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView get403() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/403");
        return mav;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView get404() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/404");
        return mav;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationExceptionResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationExceptionDto> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            ValidationExceptionDto validationExceptionDto = ValidationExceptionDto.builder()
                    .message(message)
                    .build();
            if (error instanceof FieldError) {
                String field = ((FieldError) error).getField();
                validationExceptionDto.setField(field);
            } else {
                validationExceptionDto.setField("password");
            }
            errors.add(validationExceptionDto);
        });

        return ValidationExceptionResponse.builder()
                .errors(errors)
                .build();
    }

}
