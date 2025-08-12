package com.pickban.domain.team.domain.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "league", fetch = FetchType.EAGER)
    private List<Team> teams;

}
