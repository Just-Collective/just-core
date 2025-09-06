package com.just.core.serialization.codec;

import org.junit.jupiter.api.Test;

import com.just.core.serialization.codec.impl.Codecs;
import com.just.core.serialization.codec.impl.ListCodec;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CodecTest {

    @Test
    void asList_Success() {
        // Arrange
        var codec = Codecs.INT;

        // Act
        var resultListCodec = codec.asList();

        // Assert
        assertInstanceOf(ListCodec.class, resultListCodec);
    }
}
