package com.evoter.user.model;

import com.evoter.user.Role;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue

    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "gender", nullable = true)
    private String gender;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "nin", nullable = false, unique = true)
    private String nin;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "private_key", nullable = false, unique = true, columnDefinition = "TEXT")
    private String privateKey;
    @Column(name = "public_key", nullable = false, unique = true, columnDefinition = "TEXT")
    private String publicKey;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "is_logged_in")
    private boolean isLoggedIn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }
}
