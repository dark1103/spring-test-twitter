package com.dark1103.twitter.service;

public interface FriendRequestService {

    void sendFriendRequest(String fromUsername, String toUsername);

    void acceptFriendRequest(String fromUsername, String toUsername);

    void rejectFriendRequest(String login, String username);
}
