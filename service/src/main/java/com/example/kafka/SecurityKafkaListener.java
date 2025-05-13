
package com.example.kafka;

import com.example.model.Security;
import com.example.service.SecurityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SecurityKafkaListener {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityService service;

    @KafkaListener(topics = "fixed-income-securities", groupId = "fixed-income-group")
    public void listen(String message) throws JsonProcessingException {
        Security sec = objectMapper.readValue(message, Security.class);
        service.saveSecurity(sec);
    }
}
