/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.user;

public interface UserStrategy {
    Integer calculateNumOfSwipes();

    Integer calculateDiscount(Integer initialAmount);
}
