package lets.code.moviesbattle.repository;

import lets.code.moviesbattle.model.Round;
import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Long> {

    Round findByAnswerIsFalseAndGameIdAndUserId(Long gameId, Long userId);

    Round findByGameIdAndUserIdAndAnswerFalse(Long gameId, Long userId);

}
