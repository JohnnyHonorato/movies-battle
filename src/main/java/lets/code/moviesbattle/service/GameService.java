package lets.code.moviesbattle.service;

import lets.code.moviesbattle.dto.GameDto;
import lets.code.moviesbattle.mapper.GameMapper;
import lets.code.moviesbattle.model.Game;
import lets.code.moviesbattle.model.Rank;
import lets.code.moviesbattle.repository.GameRepository;
import lets.code.moviesbattle.repository.RankRepository;
import lets.code.moviesbattle.security.TokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private final RankRepository rankRepository;

    private final TokenUtil tokenUtil;

    private final GameMapper gameMapper;

    public GameDto create(HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var game = gameRepository.findByUserIdAndIsClosedFalse(userId);
        if (Objects.isNull(game)) {
            game = gameRepository.save(Game.builder().userId(userId).totalPoint(0L).totalRound(0L).totalHits(0L).isClosed(false).build());
        }
        return gameMapper.toDto(game);
    }

    public void stopGame(HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var game = gameRepository.findByUserIdAndIsClosedFalse(userId);
        game.setIsClosed(true);
        gameRepository.save(game);
        rankRepository.save(Rank.builder().totalPoint(game.getTotalPoint()).userId(userId).build());
    }

}
