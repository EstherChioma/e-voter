package com.evoter.poll.model;

import com.evoter.candidate.model.Candidate;
import com.evoter.pollType.model.PollType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author showunmioludotun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private Integer pollTypeId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date pollDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }
//    @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Candidate> candidates;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "poll_type_id")
//    private PollType pollType;

}
