package lets.code.moviesbattle.mapper;

import lets.code.moviesbattle.dto.MovieDto;
import lets.code.moviesbattle.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    MovieDto toDto(Movie movie);

    Movie toModel(MovieDto dto);

}
