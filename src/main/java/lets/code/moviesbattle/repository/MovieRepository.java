package lets.code.moviesbattle.repository;

import lets.code.moviesbattle.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findById(Long id);
}
