package com.microservices.addressservice.kafkalistener;

import com.microservices.addressservice.constants.TopicsConstant;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = TopicsConstant.MYTOPIC,
                   groupId = "groupId")
    public void listener(String data){
        System.out.println("Received: " + data);
    }
}
