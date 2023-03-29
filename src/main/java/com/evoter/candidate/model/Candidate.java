package com.evoter.candidate.model;

import com.evoter.poll.model.Poll;
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
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "party_id", nullable = false, unique = true)
    private Integer partyId;

    @Column(name = "poll_type_id", nullable = false)
    private Integer pollTypeId;

    @Column(name = "name", nullable = false)
    private String name;
    private String gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Long voteCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "poll_id")
//    private Poll poll;
}
