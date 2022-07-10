package lets.code.moviesbattle.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoundDto {

    private Long id;

    private Long gameId;

    private Long movieId1;

    private Long movieId2;

    private String movieName1;

    private String movieName2;
}
