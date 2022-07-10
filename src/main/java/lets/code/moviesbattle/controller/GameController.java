package lets.code.moviesbattle.controller;

import io.swagger.annotations.Api;
import lets.code.moviesbattle.dto.AnswerRound;
import lets.code.moviesbattle.dto.AttemptRound;
import lets.code.moviesbattle.dto.GameDto;
import lets.code.moviesbattle.dto.RoundDto;
import lets.code.moviesbattle.service.GameService;
import lets.code.moviesbattle.service.StartRoundService;
import lets.code.moviesbattle.service.SubmitRoundService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Api
@RestController
@AllArgsConstructor
@RequestMapping(path = "/game")
public class GameController {

    private final GameService gameService;

    private final StartRoundService startRoundService;

    private final SubmitRoundService submitRoundService;

    @PostMapping
    public HttpEntity<GameDto> createGame(HttpServletRequest request) throws ParseException {
        var gameDto = gameService.create(request);
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @PostMapping("/{gameId}/endGame")
    public HttpEntity<String> stopGame(HttpServletRequest request) throws ParseException {
        gameService.stopGame(request);
        return new ResponseEntity<>("Fim de jogo", HttpStatus.OK);
    }

    @GetMapping("/{gameId}/round")
    public ResponseEntity<RoundDto> startRound(@PathVariable("gameId") Long gameId, HttpServletRequest request) throws ParseException {
        var round = startRoundService.startRound(gameId, request);
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    @PostMapping("/{gameId}/round")
    ResponseEntity<AnswerRound> submitRound(@RequestBody AttemptRound attemptRound, @PathVariable("gameId") Long gameId, HttpServletRequest request) throws ParseException {
        return new ResponseEntity<>(submitRoundService.submitRound(attemptRound, gameId, request), HttpStatus.OK);
    }


}
