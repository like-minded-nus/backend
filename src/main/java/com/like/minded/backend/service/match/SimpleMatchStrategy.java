/* LikeMinded (C)2024 */
package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.repository.match.MatchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimpleMatchStrategy implements MatchStrategy {
    @Autowired private final MatchRepository matchRepository;

    @Override
    public List<Match> findMatches(Integer profileId) {
        return matchRepository.findProfileMatches(profileId);
    }
}
