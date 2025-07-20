package com.bvanseg.just.serialization.codec;

import org.junit.jupiter.api.Test;

import com.bvanseg.just.serialization.codec.impl.Codecs;
import com.bvanseg.just.serialization.codec.impl.ListCodec;

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
