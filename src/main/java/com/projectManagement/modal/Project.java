package com.projectManagement.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectManagement.repository.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    private String category;


    @JsonIgnore
    @OneToOne(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true)
    private Chat chat;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues = new ArrayList<>();

    @ManyToMany
    private List<User>team  = new ArrayList<>();



}
