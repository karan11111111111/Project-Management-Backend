package com.projectManagement.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_table")
public class Comment {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   private String content;

   private LocalDateTime localDateTime;

   @ManyToOne
   private User user;


   @ManyToOne
   private  Issue issue;


}
