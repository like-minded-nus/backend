/* LikeMinded (C)2024 */
package com.like.minded.backend.service.user;

import lombok.Setter;

@Setter
public class UserContext {
    private UserStrategy userStrategy;

    public UserContext(UserStrategy userStrategy) {
        this.userStrategy = userStrategy;
    }

    public Integer performCalculateDiscount(Integer initialAmount) {
        return userStrategy.calculateDiscount(initialAmount);
    }
}
