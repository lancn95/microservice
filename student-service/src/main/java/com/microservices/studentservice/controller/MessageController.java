package com.microservices.studentservice.controller;

import com.microservices.studentservice.constants.TopicsConstants;
import com.microservices.studentservice.entity.MessageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("")
    public void sendMessage(@RequestBody MessageRequest request){
        kafkaTemplate.send(TopicsConstants.MYTOPIC, request.getMessage());
    }
}
