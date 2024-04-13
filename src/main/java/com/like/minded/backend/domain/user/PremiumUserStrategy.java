/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.user;

public class PremiumUserStrategy implements UserStrategy {
    @Override
    public Integer calculateNumOfSwipes() {
        return 50;
    }

    @Override
    public Integer calculateDiscount(Integer initialAmount) {
        return (int) Math.ceil(initialAmount * 1.2);
    }
}
