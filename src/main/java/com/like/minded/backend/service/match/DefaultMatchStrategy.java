package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.repository.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultMatchStrategy implements MatchStrategy {
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> findMatches(Integer profileId) {
        return matchRepository.findProfileMatches(profileId);
    }
}
