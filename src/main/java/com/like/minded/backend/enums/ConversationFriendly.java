package com.like.minded.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConversationFriendly {
    YES("Yes"),
    NO("No");

    private final String displayName;
}
