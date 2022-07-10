package lets.code.moviesbattle.controller;

import lets.code.moviesbattle.dto.RankDto;
import lets.code.moviesbattle.service.RankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/rank")
public class RankController {

    private final RankService rankService;
    
    @GetMapping
    public Iterable<RankDto> getTop10ByOrderByTotalPointDesc() {
        return rankService.getRank();
    }


}
