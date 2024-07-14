package com.projectManagement.service;

import com.projectManagement.modal.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issue, Long userId, String comment)throws Exception;
    void deleteComment(Long commentId, Long userId)throws Exception;
    List<Comment> findCommentByIssueId(Long issueId);
}
