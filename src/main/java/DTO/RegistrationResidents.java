package DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class RegistrationResidents {
    @Schema(description = "name", example = "kate")
    @Size(min = 4, max = 15, message = "name  должно содержать от 4 до 15 символов")
    @NotBlank(message = "Id не может быть пустыми")
    private String name;

    @Schema(description = "Пароль", example = "jondawfGYG")
    @Size(min = 5, max = 255, message = "Пароль должен содержать от 5 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String pass;

}
