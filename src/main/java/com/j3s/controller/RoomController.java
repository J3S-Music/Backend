package com.j3s.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class  RoomController {

    @GetMapping("/rooms/{id}")
    public String getRoom(@PathVariable("id") String id){
        return id;
    }
}
