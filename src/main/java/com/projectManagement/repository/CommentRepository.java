package com.projectManagement.repository;

import com.projectManagement.modal.Comment;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment>findByIssueId(Long issueId);

}
