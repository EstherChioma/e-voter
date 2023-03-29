package com.evoter.pollType.model;

import com.evoter.poll.model.Poll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "poll_type")
public class PollType {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

//    @OneToMany(mappedBy = "pollType", fetch = FetchType.LAZY)
//    private List<Poll> polls;

}
