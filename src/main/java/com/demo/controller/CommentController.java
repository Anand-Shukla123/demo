package com.demo.controller;

import com.demo.entity.Comments;
import com.demo.entity.Post;
import com.demo.repository.CommentsRepository;
import com.demo.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private PostRepository postRepository;
    private CommentsRepository commentRepository;

    public CommentController(CommentsRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @PostMapping
    public String createComment(@RequestBody Comments comments, @RequestParam long postId) {

        Post post = postRepository.findById(postId).get();
        comments.setPost(post);

        commentRepository.save(comments);
        return "Comment created successfully";
    }
}
