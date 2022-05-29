package ru.itis.imare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private PhotoCard photoCard;
    @Column(columnDefinition = "text")
    private String content;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
