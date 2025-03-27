package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.avro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping
    public ResponseEntity<String> sendUser(@RequestBody UserDTO userDTO) {
        try {
            User avroUser = UserMapper.toAvro(userDTO);
            System.out.println("Sending Avro user: " + avroUser);

            // Use the direct binding name from application.yml
            boolean sent = streamBridge.send("direct-output", avroUser);

            System.out.println("StreamBridge result: " + sent);

            return sent
                    ? ResponseEntity.ok("User sent to Kafka")
                    : ResponseEntity.status(500).body("Failed to send user");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}