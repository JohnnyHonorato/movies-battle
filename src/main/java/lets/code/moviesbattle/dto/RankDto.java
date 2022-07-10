package lets.code.moviesbattle.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankDto {

    private Long id;

    private Long userId;

    private Long totalPoint;

}
