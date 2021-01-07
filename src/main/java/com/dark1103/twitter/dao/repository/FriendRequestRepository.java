package com.dark1103.twitter.dao.repository;

import com.dark1103.twitter.dao.entity.FriendRequest;
import com.dark1103.twitter.dao.entity.RequestStatus;
import com.dark1103.twitter.dao.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {

    FriendRequest findFirstByFromAndToAndStatus(User from, User to, RequestStatus status);

}
