package com.dark1103.twitter.api;

import com.dark1103.twitter.dao.entity.FriendRequest;
import com.dark1103.twitter.dao.entity.User;
import com.dark1103.twitter.payload.request.FriendWebRequest;
import com.dark1103.twitter.service.AuthenticationService;
import com.dark1103.twitter.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final FriendRequestService friendRequestService;
    private final AuthenticationService authenticationService;

    @GetMapping("/friends")
    public Set<User> listFriends() {
        return authenticationService.getCurrentUser().getFriends();
    }

    @GetMapping("/friends/requests")
    public Set<FriendRequest> friendRequests() {
        return authenticationService.getCurrentUser().getReceivedRequests();
    }

    @PostMapping("/friends/add")
    public void sendFriendRequest(@RequestBody FriendWebRequest friendRequest) {
        friendRequestService.sendFriendRequest(authenticationService.getCurrentUser().getLogin(), friendRequest.getUsername());
    }

    @PostMapping("/friends/acceptRequest")
    public void acceptFriendRequest(@RequestBody FriendWebRequest friendRequest){
        friendRequestService.acceptFriendRequest(authenticationService.getCurrentUser().getLogin(), friendRequest.getUsername());
    }

    @PostMapping("/friends/rejectRequest")
    public void rejectFriendRequest(@RequestBody FriendWebRequest friendRequest){
        friendRequestService.rejectFriendRequest(authenticationService.getCurrentUser().getLogin(), friendRequest.getUsername());
    }

}
