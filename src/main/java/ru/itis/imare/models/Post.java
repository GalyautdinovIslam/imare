package ru.itis.imare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Account owner;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @OneToMany(mappedBy = "post")@Builder.Default
    private List<PhotoCard> photoCards = new ArrayList<>();
}
