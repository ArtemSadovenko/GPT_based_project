package com.company.proj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat/")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/start")
    public void start(){
        chatService.start();
    }

    @PostMapping
    public void giveWord(@RequestBody String word){

    }
}
