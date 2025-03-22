package com.bvanseg.just.concurrency;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MPSCChannelTest {

    @Test
    void send_Succeeds_WhenOpen() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // act
        var result = channel.send("test");
        // Assert
        assertTrue(result.isOk());
    }

    @Test
    void send_Fails_WhenClosed() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.close();
        var result = channel.send("test");
        // Assert
        assertTrue(result.isErr());
        assertInstanceOf(MPSCChannel.SendError.Closed.class, result.unwrapErr());
    }

    @Test
    void sendTimeout_Succeeds_WhenSpaceAvailable() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        var result = channel.send("value", Duration.ofSeconds(1));
        // Assert
        assertTrue(result.isOk());
    }

    @Test
    void sendTimeout_TimesOut_WhenQueueFull() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.send("filled");
        var result = channel.send("overflow", Duration.ofMillis(10));
        // Assert
        assertTrue(result.isErr());
        assertInstanceOf(MPSCChannel.SendTimeoutError.Timeout.class, result.unwrapErr());
    }

    @Test
    void receive_Succeeds_AfterSend() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.send("hello");
        var result = channel.receive();
        // Assert
        assertTrue(result.isOk());
        assertEquals("hello", result.unwrap());
    }

    @Test
    void receiveTimeout_Succeeds_WhenItemAvailable() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.send("hi");
        var result = channel.receive(Duration.ofSeconds(1));
        // Assert
        assertTrue(result.isOk());
        assertEquals("hi", result.unwrap());
    }

    @Test
    void receiveTimeout_TimesOut_WhenQueueEmpty() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        var result = channel.receive(Duration.ofMillis(10));
        // Assert
        assertTrue(result.isErr());
        assertInstanceOf(MPSCChannel.ReceiveTimeoutError.Timeout.class, result.unwrapErr());
    }

    @Test
    void trySend_Succeeds_WhenOpen() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act & Assert
        assertTrue(channel.trySend("msg"));
    }

    @Test
    void trySend_Fails_WhenClosed() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.close();
        // Assert
        assertFalse(channel.trySend("msg"));
    }

    @Test
    void tryReceive_ReturnsItem_IfAvailable() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.send("data");
        var result = channel.tryReceive();
        // Assert
        assertTrue(result.isSome());
        assertEquals("data", result.unwrap());
    }

    @Test
    void tryReceive_ReturnsNone_IfQueueEmpty() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        var result = channel.tryReceive();
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void isEmpty_ReturnsTrue_WhenEmpty() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act & Assert
        assertTrue(channel.isEmpty());
    }

    @Test
    void isEmpty_ReturnsFalse_WhenNotEmpty() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act
        channel.send("filled");
        // Assert
        assertFalse(channel.isEmpty());
    }

    @Test
    void isClosed_ReturnsCorrectState() {
        // Arrange
        var channel = MPSCChannel.bounded(1);
        // Act & Assert
        assertFalse(channel.isClosed());
        channel.close();
        assertTrue(channel.isClosed());
    }

    @Test
    void size_ReturnsCorrectSize() {
        // Arrange
        var channel = MPSCChannel.bounded(5);
        // Act & Assert
        assertEquals(0, channel.size());
        channel.send("one");
        assertEquals(1, channel.size());
    }
}
