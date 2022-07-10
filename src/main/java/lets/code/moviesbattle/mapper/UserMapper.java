package lets.code.moviesbattle.mapper;

import lets.code.moviesbattle.dto.UserDto;
import lets.code.moviesbattle.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);
}
