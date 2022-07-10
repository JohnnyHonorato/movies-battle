package lets.code.moviesbattle.repository;

import lets.code.moviesbattle.model.Rank;
import org.springframework.data.repository.CrudRepository;

public interface RankRepository extends CrudRepository<Rank, Long> {

    Iterable<Rank> findTop10ByOrderByTotalPointDesc();
}
