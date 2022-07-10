package lets.code.moviesbattle.controller;

import lets.code.moviesbattle.dto.UserDto;
import lets.code.moviesbattle.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createNote(@RequestBody UserDto dto) {
        var user = userService.create(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
