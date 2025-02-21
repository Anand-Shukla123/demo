package com.demo.controller;


import com.demo.entity.Post;
import com.demo.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping
    public String createPost(@RequestBody Post post) {

        postRepository.save(post);
        return "saved";
    }

    @DeleteMapping
    public String deletePost(@RequestParam long postId) {
        postRepository.deleteById(postId);
        return "delete";

    }
}
