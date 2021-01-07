package com.dark1103.twitter.service;

import com.dark1103.twitter.dao.entity.Post;
import com.dark1103.twitter.dao.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface PostService {

    Set<Post> getPostsByDateAfter(LocalDateTime dateTime);

    List<Post> getPostsByAuthor(User user);

    void addPost(Post post);

    List<Post> getFeedForUser(User user);

//    List<Post> getFeedForUser(Long userId);
//    List<Post> getFeedForUser(String userName);

}
