package com.bvanseg.just.concurrency;

import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MPSCChannelTest {

    @Test
    void send_Success() throws InterruptedException {
        // Arrange
        var channel = new MPSCChannel<String>();

        // Act
        var thread1 = new Thread(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(1));
                channel.send("Hello, thread!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread2 = new Thread(() -> {
            try {
                System.out.println("Receiving...");
                var message = channel.receive();
                System.out.println("Received. Message: " + message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // Assert
    }
}
