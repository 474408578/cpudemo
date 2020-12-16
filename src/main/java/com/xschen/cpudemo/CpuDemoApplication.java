package com.xschen.cpudemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author xschen
 */

@SpringBootApplication
@EnableScheduling
public class CpuDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CpuDemoApplication.class, args);
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(4096);
    private int count;


    @Scheduled(fixedRate = 10)
    public void lockContention() {
        IntStream.range(0, 100000)
                .forEach(i -> executorService.submit(this::incrementSync));
    }

    private synchronized void incrementSync() {
        count = (count + 1) % 100000;
    }
}
