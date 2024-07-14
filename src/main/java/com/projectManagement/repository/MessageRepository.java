package com.projectManagement.repository;

import com.projectManagement.modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatIdOrderByCreateAtAsc(Long chatId);
}
