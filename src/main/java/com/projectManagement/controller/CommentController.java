package com.projectManagement.controller;

import com.projectManagement.modal.Comment;
import com.projectManagement.modal.User;
import com.projectManagement.request.CreateCommentRequest;
import com.projectManagement.response.MessageResponse;
import com.projectManagement.service.CommentService;
import com.projectManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment>createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserProfilByJwt(jwt);
        Comment createdComment = commentService.createComment(req.getIssueId(),
                user.getId(), req.getContent());
        return  new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse>deleteComment(@PathVariable Long commentId,
                                                        @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserProfilByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse res = new MessageResponse();
        res.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId){
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }



}
