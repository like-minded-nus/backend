package com.like.minded.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VendorType {
    INDOOR("Indoor"),
    OUTDOOR("Outdoor");

    private final String displayName;
}
