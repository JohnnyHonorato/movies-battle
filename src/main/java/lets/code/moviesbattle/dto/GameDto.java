package lets.code.moviesbattle.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {

    private Long id;

    private Long userId;

    private Long totalPoint;

    private Long totalRound;

    private Long totalHits;

}
