package com.dark1103.twitter.service.impl;

import com.dark1103.twitter.dao.entity.Post;
import com.dark1103.twitter.dao.entity.User;
import com.dark1103.twitter.dao.repository.PostRepository;
import com.dark1103.twitter.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Set<Post> getPostsByDateAfter(LocalDateTime dateTime) {
        return postRepository.getPostByDateTimeAfter(dateTime);
    }

    @Override
    public List<Post> getPostsByAuthor(User user) {
        return postRepository.getPostByAuthorOrderByDateTime(user);
    }

    @Override
    public void addPost(Post post) {
        post.setDateTime(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public List<Post> getFeedForUser(User user) {
        Set<User> userSet = user.getFriends();
        userSet.add(user);

        return userSet.stream().flatMap(user1 -> user1.getPosts().stream()).sorted((post1, post2) -> {
            if (post1.getDateTime() == post2.getDateTime()) {
                return 0;
            } else if (post1.getDateTime() == null) {
                return 1;
            } else if (post2.getDateTime() == null) {
                return -1;
            } else {
                return post1.getDateTime().compareTo(post2.getDateTime());
            }
        }).collect(Collectors.toList());
    }

}
