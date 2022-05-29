package ru.itis.imare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String information;
    @OneToMany(mappedBy = "following")
    @Builder.Default
    private List<Subscription> followers = new ArrayList<>();
    @OneToMany(mappedBy = "follower")@Builder.Default
    private List<Subscription> following= new ArrayList<>();
    @OneToMany(mappedBy = "owner")@Builder.Default
    private List<Post> posts= new ArrayList<>();
    @OneToMany(mappedBy = "user")@Builder.Default
    private List<Comment> comments= new ArrayList<>();
    @ManyToMany(mappedBy = "tags")@Builder.Default
    private List<PhotoCard> tags= new ArrayList<>();
    @OneToMany(mappedBy = "reactor")@Builder.Default
    private List<Reaction> reactions= new ArrayList<>();
    @OneToMany(mappedBy = "owner")@Builder.Default
    private List<Album> albums= new ArrayList<>();
}
