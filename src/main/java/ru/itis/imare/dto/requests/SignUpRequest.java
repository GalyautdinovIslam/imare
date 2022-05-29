package ru.itis.imare.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.validation.annotations.FieldsMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldsMatch(fields = {"password", "repeatPassword"}, message = "Пароли не совпадают")
public class SignUpRequest {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(max = 32, min = 6, message = "Имя пользователя должно быть не меньше 6 и не больше 32 символов")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, message = "Пароль не должен быть меньше 8 символов")
    private String password;
    private String repeatPassword;
    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    private String information;
}
