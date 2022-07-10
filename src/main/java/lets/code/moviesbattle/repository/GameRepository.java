package lets.code.moviesbattle.repository;

import lets.code.moviesbattle.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByUserIdAndIsClosedFalse(Long id);

    Game findByUserIdAndIdAndIsClosedFalse(Long userId, Long id);
}
