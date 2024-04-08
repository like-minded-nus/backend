package com.like.minded.backend.domain.user;

import com.like.minded.backend.domain.CreatedUpdatedColumns;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User extends CreatedUpdatedColumns {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name="user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name="USER_ID")
    private Integer userId;

    @NonNull
    @Column(name="USERNAME")
    private String username;

    @NonNull
    @Column(name="PASSWORD")
    private String password;

    @NonNull
    @Column(name="EMAIL")
    private String email;

    @NonNull
    @ManyToOne
    @JoinColumn(name="ROLE_TYPE")
    private UserRole userRole;

    @NonNull
    @Column(name="IS_PREMIUM")
    private Integer isPremium;

}
