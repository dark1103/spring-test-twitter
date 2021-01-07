package com.dark1103.twitter.dao.repository;

import com.dark1103.twitter.dao.entity.Post;
import com.dark1103.twitter.dao.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface PostRepository extends CrudRepository<Post, Long> {

    Set<Post> getPostByDateTimeAfter(LocalDateTime dateTime);

    List<Post> getPostByAuthorOrderByDateTime(User author);

//    @Query("SELECT p from Post p where p.author.id in :#{#user.friends.toArray()} order by p.dateTime")
//    List<Post> getFeedForUser(@Param("user") User user);

}
