package com.projectManagement.request;

import lombok.Data;

@Data  //getter setter method
public class CreateCommentRequest {
    private Long issueId;
    private String content;
}
