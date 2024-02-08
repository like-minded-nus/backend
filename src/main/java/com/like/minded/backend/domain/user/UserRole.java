package com.like.minded.backend.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @NonNull
    @Column(name="ROLE_TYPE")
    private Integer roleType;

    @NonNull
    @Column(name="ROLE_DESC")
    private String roleDesc;
}
