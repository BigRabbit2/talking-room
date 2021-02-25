package com.czm.talkingroom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("hello")
public class ExampleController {

    private Map<String, Set<String>> cache = new HashMap();
    
    @PostMapping("join")
    public Set<String> joinRoom(String roomId, String userId) {
        Set<String> userIds;
        Set<String> results = new HashSet<>();
        if ((userIds = cache.get(roomId)) == null) {
            userIds = new HashSet<>();
            userIds.add(userId);
            cache.put(roomId,userIds);
        } else {
            results = userIds;
            userIds.add(userId);
        }
        return results;
    }
    @PostMapping("leave")
    public Set<String> leaveRoom(String roomId, String userId) throws Exception {
        Set<String> userIds;
        if ((userIds = cache.get(roomId)) == null) {
            throw new Exception();
        } else {
            userIds.remove(userId);
        }
        return userIds;
    }
}
