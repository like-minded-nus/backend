/* LikeMinded (C)2024 */
package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.repository.match.MatchRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleMatchStrategy implements MatchStrategy {

    MatchRepository matchRepository;

    @Override
    public List<Match> findMatches(Integer profileId) {
        return matchRepository.findProfileMatches(profileId);
    }
}
