package com.evoter.vote.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author showunmioludotun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(name = "encrypted_aes_key", nullable = false, unique = true, columnDefinition = "TEXT")
    private String encryptedAesKey;
    @Column(name = "encrypted_vote_data", nullable = false, unique = true, columnDefinition = "TEXT")
    private String encryptedVoteData;

    @Column(nullable = false)
    private Integer pollId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }
}
