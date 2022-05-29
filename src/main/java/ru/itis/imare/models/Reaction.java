package ru.itis.imare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reaction {

    public enum Type {
        LIKE, DISLIKE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account reactor;
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private PhotoCard photo;
    @Enumerated(value = EnumType.STRING)
    private Type type;
}
