package com.projectManagement.repository;

import com.projectManagement.modal.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);
}
