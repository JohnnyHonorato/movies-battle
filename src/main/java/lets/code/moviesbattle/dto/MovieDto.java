package lets.code.moviesbattle.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDto {

    private Long id;

    private String title;

    private Float numberOfVotes;
    
    private Long averageOfVotes;
}
