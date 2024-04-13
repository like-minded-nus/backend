/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.user;

public class NormalUserStrategy implements UserStrategy {
    @Override
    public Integer calculateNumOfSwipes() {
        return 10;
    }

    @Override
    public Integer calculateDiscount(Integer initialAmount) {
        // no extra discount for non-premium user
        return initialAmount;
    }
}
