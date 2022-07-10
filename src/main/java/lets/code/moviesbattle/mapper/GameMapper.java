package lets.code.moviesbattle.mapper;

import lets.code.moviesbattle.dto.GameDto;
import lets.code.moviesbattle.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {

    GameDto toDto(Game game);

    Game toModel(GameDto dto);

}
