package lets.code.moviesbattle.controller;

import lets.code.moviesbattle.dto.UserDto;
import lets.code.moviesbattle.model.User;
import lets.code.moviesbattle.security.AuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    public AuthenticateController(final AuthenticateService service) {
        this.authenticateService = service;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody final UserDto dto) {
        final User user = this.authenticateService.authenticate(dto.getUsername(), dto.getPassword());
        final String jwt = this.authenticateService.createJwtToken(user);
        return new ResponseEntity<>(UserDto.builder().token(jwt).name(user.getName()).username(user.getUsername()).build(), HttpStatus.OK);
    }

}
