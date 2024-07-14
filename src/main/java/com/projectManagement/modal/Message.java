package com.projectManagement.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;
}
