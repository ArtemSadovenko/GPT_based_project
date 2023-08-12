package com.company.proj;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat/")
public class Controller {

    @PostMapping
    public void start(){

    }

    @PostMapping
    public ResponseBody giveWord(@RequestBody String word){

    }
}
