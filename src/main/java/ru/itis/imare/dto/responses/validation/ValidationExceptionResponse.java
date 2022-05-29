package ru.itis.imare.dto.responses.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ValidationExceptionResponse {
    private List<ValidationExceptionDto> errors;
}
