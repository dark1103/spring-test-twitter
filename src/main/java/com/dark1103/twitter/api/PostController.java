package com.dark1103.twitter.api;

import com.dark1103.twitter.dao.entity.Post;
import com.dark1103.twitter.service.AuthenticationService;
import com.dark1103.twitter.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public List<Post> getFeed() {
        return postService.getFeedForUser(authenticationService.getCurrentUser());
    }

    @GetMapping("/my")
    public List<Post> getUsersPosts(){
        return postService.getPostsByAuthor(authenticationService.getCurrentUser());
    }

    @PostMapping
    public void add(@RequestBody Post post) {
        postService.addPost(post);
    }

}
