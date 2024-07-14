package com.projectManagement.service;

import com.projectManagement.modal.Issue;
import com.projectManagement.modal.User;
import com.projectManagement.request.IssueRequest;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId)throws Exception;

    List<Issue> getIssueByProjectId(Long projectId)throws Exception;

    Issue createIssue(IssueRequest issue, User user)throws Exception;

//    Optional<Issue>updateIssue(Long issueId, IssueRequest updateIssue, Long userid) throws Exception;

    void deleteIssue(Long issueId, Long userId)throws Exception;

    Issue addUserToIssue(Long issueId, Long userId)throws Exception;

    Issue updateStatus(Long issueId, String status)throws Exception;

}
