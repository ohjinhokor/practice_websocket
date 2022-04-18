package com.websocket.socket_example.stopwatch;

import org.springframework.util.StopWatch;

import java.math.BigDecimal;

public class StopWatchExample {

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();

        BigDecimal bigDecimal = BigDecimal.valueOf(0, 0);
        Long longType = 0L;

        stopWatch.start();
        for(int i = 0; i < 1_000_000; i++) {
            longType += 1L;
        }
        stopWatch.stop();

        stopWatch.start("BigDecimal type");
        for(int i = 0; i < 1_000_000; i++) {
            bigDecimal = bigDecimal.add(BigDecimal.ONE);
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
