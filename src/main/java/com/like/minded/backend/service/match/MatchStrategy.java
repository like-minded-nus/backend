package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;

import java.util.List;

/**
 * Given the possibility of different matching strategies based on user preferences or other criteria,
 * the Strategy pattern allows the selection of the matching algorithm at runtime.
 * This makes your matching logic more flexible and easily extensible.
 * <br/><br/>
 * For instance, if in the future you decide to introduce a new matching criteria,
 * you can simply add a new strategy without modifying the existing code.
 **/
public interface MatchStrategy {
    List<Match> findMatches(Integer profileId);
}
