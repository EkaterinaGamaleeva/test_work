package Servises;

import DTO.AutentificationResidents;
import DTO.RegistrationResidents;
import DTO.ResponseAutentification;
import lombok.RequiredArgsConstructor;
import model.Residents;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiseAuthentication {
    private final ServiseResudents resudentsServise;
    private final JwtServis jwtServis;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private ResponseAutentification request;

    public ResponseAutentification registrationResidents(RegistrationResidents request) {

        var user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPass()))
                .build();

        resudentsServise.addResidetsHouse((Residents) user);

        var jwt = JwtServis.generateToken(user);
        return new ResponseAutentification(jwt);
    }

    public ResponseAutentification autentificationResidents ( AutentificationResidents request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getName(),
                request.getPass()
        ));

        var user = resudentsServise.userDetailsService()
                .loadUserByUsername(request.getName());

        var jwt = JwtServis.generateToken(user);
        return new ResponseAutentification(jwt);
    }
}
