package lets.code.moviesbattle.service;

import lets.code.moviesbattle.dto.AnswerRound;
import lets.code.moviesbattle.dto.AttemptRound;
import lets.code.moviesbattle.model.Movie;
import lets.code.moviesbattle.model.Round;
import lets.code.moviesbattle.repository.GameRepository;
import lets.code.moviesbattle.repository.MovieRepository;
import lets.code.moviesbattle.repository.RoundRepository;
import lets.code.moviesbattle.security.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubmitRoundService {

    private final TokenUtil tokenUtil;

    private final GameRepository gameRepository;

    private final MovieRepository movieRepository;

    private final RoundRepository roundRepository;

    public AnswerRound submitRound(AttemptRound attemptRound, Long gameId, HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var game = gameRepository.findByUserIdAndIdAndIsClosedFalse(userId, gameId);
        var round = roundRepository.findByGameIdAndUserIdAndAnswerFalse(gameId, userId);
        var msg = "Você errou!";

        if (Objects.isNull(game)) {
            return AnswerRound.builder().msg("JOGO NÃO CRIADO").build();
        }

        if (validarRound(round)) {
            return AnswerRound.builder().msg("JA ACERTOU A RESPOSTA, VA PARA OUTRA RODADA").build();
        }

        if (round.getRoundNumber() > 3) {
            round.setAnswer(true);
            roundRepository.save(round);
            return AnswerRound.builder().msg("JA ESGOTOU O NUMERO DE TENTATIVAS, VA PARA OUTRA RODADA").build();
        }

        var rightAnswer = getRightAnswer(movieRepository.findById(round.getMovieId1()).get(), movieRepository.findById(round.getMovieId2()).get());
        game.setTotalRound(game.getTotalRound() + 1);
        round.setRoundNumber(round.getRoundNumber() + 1);
        if (attemptRound.getOption() == rightAnswer) {
            game.setTotalHits(game.getTotalHits() + 1);
            game.setTotalPoint(game.getTotalRound() * ((game.getTotalRound() * 100) / game.getTotalHits()));
            round.setAnswer(true);
            roundRepository.save(round);
            msg = "Você acertou!";
        }

        gameRepository.save(game);
        return AnswerRound.builder().msg(msg).build();

    }

    private boolean validarRound(Round round) {
        if (Objects.isNull(round)) {
            return true;
        }

        if (round.getAnswer() == true) {
            return true;
        }
        return false;
    }

    private Integer getRightAnswer(Movie movie1, Movie movie2) {
        var amountMovie1 = movie1.getAverageOfVotes() * movie1.getNumberOfVotes();
        var amountMovie2 = movie2.getAverageOfVotes() * movie2.getNumberOfVotes();
        if (amountMovie1 > amountMovie2) {
            return 1;
        } else {
            return 2;
        }
    }
}
