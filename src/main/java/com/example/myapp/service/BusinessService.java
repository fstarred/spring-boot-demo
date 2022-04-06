package com.example.myapp.service;

import com.example.myapp.business.RandomGenerator;
import com.example.myapp.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@Service
public class BusinessService {

    @Autowired
    Logger logger;

    @Autowired
    RandomGenerator generator;

    public int aggregateRandomNumbers() {
        logger.info("starting service at sec: {}",  LocalDateTime.now().get(ChronoField.SECOND_OF_MINUTE));
        final Stream<CompletableFuture<Integer>> xs = Stream.of(
                generator.generateRandomNumberAndWait(500),
                generator.generateRandomNumberAndWait(1000),
                generator.generateRandomNumberAndWait(2000)
        );

        final CompletableFuture<Integer> sum = xs.reduce(CompletableFuture.completedFuture(0),
                (x, y) -> x.thenCombine(y, Integer::sum));

        try {
            final int output = sum.get();
            logger.info("finished computing service at sec: {}",  LocalDateTime.now().get(ChronoField.SECOND_OF_MINUTE));
            return output;
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new InternalServerErrorException(e);
        }
    }



}
