package com.bvanseg.just.concurrency;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import com.bvanseg.just.functional.result.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MPSCChannelTest {

    @Test
    void send_AndReceive_MessageIsDeliveredBetweenThreads() {
        // Arrange
        var channel = MPSCChannel.<String>unbounded();
        var receivedMessage = new AtomicReference<String>();

        // Act
        var thread1 = new Thread(
            () -> Result.tryRun(() -> Thread.sleep(Duration.ofMillis(50)))
                .ifOk(_ -> channel.send("Hello, thread!"))
        );

        var thread2 = new Thread(() -> {
            var result = channel.receive();
            assertTrue(result.isOk());
            result.ifOk(receivedMessage::set);
        });

        thread1.start();
        thread2.start();

        var joinResult1 = Result.trySupply(() -> thread1.join(Duration.ofMillis(200)));
        var joinResult2 = Result.trySupply(() -> thread2.join(Duration.ofMillis(200)));

        // Assert
        assertTrue(joinResult1.isOk());
        assertTrue(joinResult2.isOk());
        assertEquals("Hello, thread!", receivedMessage.get());
    }
}
