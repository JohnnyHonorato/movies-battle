package lets.code.moviesbattle.service;

import lets.code.moviesbattle.dto.RankDto;
import lets.code.moviesbattle.mapper.RankMapper;
import lets.code.moviesbattle.repository.RankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RankService {

    private final RankRepository rankRepository;

    private final RankMapper rankMapper;

    public Iterable<RankDto> getRank() {
        return rankMapper.toDto(rankRepository.findTop10ByOrderByTotalPointDesc());
    }

}
