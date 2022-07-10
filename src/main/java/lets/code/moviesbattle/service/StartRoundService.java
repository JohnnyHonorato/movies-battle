package lets.code.moviesbattle.service;

import lets.code.moviesbattle.dto.MovieDto;
import lets.code.moviesbattle.dto.RoundDto;
import lets.code.moviesbattle.mapper.RoundMapper;
import lets.code.moviesbattle.model.Game;
import lets.code.moviesbattle.model.Round;
import lets.code.moviesbattle.repository.GameRepository;
import lets.code.moviesbattle.repository.MovieRepository;
import lets.code.moviesbattle.repository.RoundRepository;
import lets.code.moviesbattle.security.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StartRoundService {

    private final TokenUtil tokenUtil;

    private final RoundRepository roundRepository;

    private final GameRepository gameRepository;

    private final MovieRepository movieRepository;

    private final RandomMoviesUtilService randomMoviesUtilService;

    private final RoundMapper roundMapper;

    public RoundDto startRound(Long gameId, HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var round = roundRepository.findByAnswerIsFalseAndGameIdAndUserId(gameId, userId);
        if (Objects.isNull(round)) {
            var game = gameRepository.findByUserIdAndIdAndIsClosedFalse(userId, gameId);
            if (Objects.nonNull(game)) {
                var listMovies = randomMoviesUtilService.getMovies(game.getId());
                round = saveRound(userId, game, listMovies);
                game.setTotalRound(round.getRoundNumber());
            }
        }
        return getRoundDto(round);
    }

    private Round saveRound(Long userId, Game game, List<MovieDto> listMovies) {
        var round = Round.builder()
                .gameId(game.getId())
                .roundNumber(1L)
                .movieId1(listMovies.get(0).getId())
                .movieId2(listMovies.get(1).getId())
                .answer(false)
                .userId(userId)
                .build();
        return roundRepository.save(round);
    }

    private RoundDto getRoundDto(Round newRound) {
        var roundDto = roundMapper.toDto(newRound);
        roundDto.setMovieName1(movieRepository.findById(roundDto.getMovieId1()).get().getTitle());
        roundDto.setMovieName2(movieRepository.findById(roundDto.getMovieId2()).get().getTitle());
        return roundDto;
    }


}
