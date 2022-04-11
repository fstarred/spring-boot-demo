package com.example.myapp.api;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * Send kafka messages
 */
@RestController
@RequestMapping("/messages")
public class MessageApi {

    @Autowired
    Logger logger;

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void send(@RequestBody @NotBlank String message) {
        logger.info("sending message: {}", message);
        streamBridge.send("sendMessage-out-0", message);
    }

}
