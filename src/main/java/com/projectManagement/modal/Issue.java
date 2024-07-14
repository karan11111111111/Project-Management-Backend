package com.projectManagement.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   private String title;
   private String description;
   private String status;

   private Long projectID;
   private String priority;
   private LocalDate dueDate;

   private List<String> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @JsonIgnore
    @ManyToOne
    private Project project;

    @JsonIgnore  //we give JasonIgnore to avoid recursion problem
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
