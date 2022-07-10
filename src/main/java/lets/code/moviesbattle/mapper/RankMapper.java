package lets.code.moviesbattle.mapper;

import lets.code.moviesbattle.dto.RankDto;
import lets.code.moviesbattle.model.Rank;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RankMapper {

    Iterable<RankDto> toDto(Iterable<Rank> rank);

}
