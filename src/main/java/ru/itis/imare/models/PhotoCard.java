package ru.itis.imare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PhotoCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToOne
    @JoinColumn(name = "file_id")
    private FileInfo photo;
    @OneToMany(mappedBy = "photoCard")@Builder.Default
    private List<Comment> comments = new ArrayList<>();
    @ManyToMany@Builder.Default
    @JoinTable(joinColumns = @JoinColumn(name = "photocard_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private List<Account> tags = new ArrayList<>();
    private Float latitude; // y (0 - 90)
    private Float longitude; // x (0 - 180)
    @OneToMany(mappedBy = "photo")@Builder.Default
    private List<Reaction> reactions = new ArrayList<>();
    @ManyToMany(mappedBy = "photoCards")@Builder.Default
    private List<Album> albums = new ArrayList<>();
}
