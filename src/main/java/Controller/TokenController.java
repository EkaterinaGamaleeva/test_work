package Controller;

import DTO.AutentificationResidents;
import DTO.RegistrationResidents;
import DTO.ResponseAutentification;
import Servises.ServiseAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class TokenController {
        private final ServiseAuthentication authenticationService;

        @Operation(summary = "Регистрация пользователя")
        @PostMapping("/sign-up")
        public ResponseAutentification signUp(@RequestBody @Valid RegistrationResidents request) {
            return authenticationService.registrationResidents(request);
        }

        @Operation(summary = "Авторизация пользователя")
        @PostMapping("/sign-in")
        public ResponseAutentification  signIn(@RequestBody @Valid AutentificationResidents request) {
            return authenticationService.autentificationResidents(request);
        }
    }

