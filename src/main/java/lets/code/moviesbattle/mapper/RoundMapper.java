package lets.code.moviesbattle.mapper;

import lets.code.moviesbattle.dto.RoundDto;
import lets.code.moviesbattle.model.Round;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoundMapper {

    RoundDto toDto(Round round);

    Round toModel(RoundDto dto);
}
