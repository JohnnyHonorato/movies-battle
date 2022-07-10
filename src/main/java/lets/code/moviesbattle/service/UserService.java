package lets.code.moviesbattle.service;

import lets.code.moviesbattle.dto.UserDto;
import lets.code.moviesbattle.mapper.UserMapper;
import lets.code.moviesbattle.model.User;
import lets.code.moviesbattle.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto create(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        var user = userRepository.save(userMapper.toModel(userDto));
        return userMapper.toDto(user);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

