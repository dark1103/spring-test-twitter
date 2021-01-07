package com.dark1103.twitter.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(schema = "json", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    @JsonIgnore
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "json", name = "friends", joinColumns = {@JoinColumn(name = "user_1")}, inverseJoinColumns = {@JoinColumn(name = "user_2")})
    private Set<User> friends = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    private Set<FriendRequest> sentRequests = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    private Set<FriendRequest> receivedRequests = new HashSet<>();
}
