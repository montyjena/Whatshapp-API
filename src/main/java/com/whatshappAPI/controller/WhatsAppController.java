package com.whatshappAPI.controller;

import com.whatshappAPI.payload.WhatsAppMessageRequest;
import com.whatshappAPI.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    private final TwilioService twilioService;

    @Autowired
    public WhatsAppController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendWhatsAppMessage(@RequestBody WhatsAppMessageRequest request) {
        try {
            twilioService.sendWhatsAppMessage(request.getTo(), request.getMessage());
            return ResponseEntity.ok("Message sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message: " + e.getMessage());
        }
    }
}
