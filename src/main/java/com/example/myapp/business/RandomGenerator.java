package com.example.myapp.business;

import com.example.myapp.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Component
public class RandomGenerator {

    @Autowired
    Logger logger;

    private final Random random = new Random();

    @Async
    public CompletableFuture<Integer> generateRandomNumberAndWait(int wait) {

        try {
            logger.info("waiting: {} ms", wait);
            Thread.sleep(wait);
            logger.info("finished to wait: {} ms", wait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InternalServerErrorException(e);
        }
        final int value = random.nextInt(100);
        logger.info("generated random value: {}", value);
        return CompletableFuture.completedFuture(value);

    }

}
