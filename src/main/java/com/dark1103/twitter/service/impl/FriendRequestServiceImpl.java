package com.dark1103.twitter.service.impl;

import com.dark1103.twitter.dao.entity.FriendRequest;
import com.dark1103.twitter.dao.entity.RequestStatus;
import com.dark1103.twitter.dao.entity.User;
import com.dark1103.twitter.dao.repository.FriendRequestRepository;
import com.dark1103.twitter.dao.repository.UserRepository;
import com.dark1103.twitter.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void sendFriendRequest(String fromUsername, String toUsername) {

        if(fromUsername.equals(toUsername)){
            throw new RuntimeException("You can't send friend request to yourself");
        }

        FriendRequest friendRequest = new FriendRequest();
        User fromUser = userRepository.findByLogin(fromUsername);
        User toUser = userRepository.findByLogin(toUsername);

        if(fromUser.getFriends().contains(toUser)){
            throw new RuntimeException(fromUser.toString() + " and " + toUsername.toString() + " are already friends");
        }

        if(friendRequestRepository.findFirstByFromAndToAndStatus(fromUser, toUser, RequestStatus.PENDING) != null){
            throw new RuntimeException(fromUser.toString() + " already sent request to " + toUsername.toString());
        }

        FriendRequest inverseRequest = friendRequestRepository.findFirstByFromAndToAndStatus(toUser, fromUser, RequestStatus.PENDING);
        if(inverseRequest != null){
            inverseRequest.setStatus(RequestStatus.APPROVED);

            toUser.getFriends().add(fromUser);
            fromUser.getFriends().add(toUser);

        }else{
            friendRequest.setFrom(fromUser);
            friendRequest.setTo(toUser);

            friendRequest.setStatus(RequestStatus.PENDING);
            friendRequest.setDateTime(LocalDateTime.now());

            friendRequestRepository.save(friendRequest);
        }
    }

    @Override
    @Transactional
    public void acceptFriendRequest(String fromUsername, String toUsername) {
        User fromUser = userRepository.findByLogin(fromUsername);
        User toUser = userRepository.findByLogin(toUsername);

        FriendRequest inverseRequest = friendRequestRepository.findFirstByFromAndToAndStatus(fromUser, toUser, RequestStatus.PENDING);
        if(inverseRequest == null) {
            throw new RuntimeException("Friend request not found");
        }

        inverseRequest.setStatus(RequestStatus.APPROVED);
        toUser.getFriends().add(fromUser);
        fromUser.getFriends().add(toUser);
    }

    @Override
    @Transactional
    public void rejectFriendRequest(String fromUsername, String toUsername) {
        User fromUser = userRepository.findByLogin(fromUsername);
        User toUser = userRepository.findByLogin(toUsername);

        FriendRequest inverseRequest = friendRequestRepository.findFirstByFromAndToAndStatus(fromUser, toUser, RequestStatus.PENDING);
        if(inverseRequest == null) {
            throw new RuntimeException("Friend request not found");
        }

        inverseRequest.setStatus(RequestStatus.REJECTED);
    }

}
