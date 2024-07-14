package com.projectManagement.repository;

import com.projectManagement.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
