package ru.itis.imare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Album {

    public enum Type {
        MAIN, COMMON
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account owner;
    private String title;
    @ManyToMany@Builder.Default
    @JoinTable(
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "photocard_id", referencedColumnName = "id"))
    private List<PhotoCard> photoCards = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private Type type;
}
