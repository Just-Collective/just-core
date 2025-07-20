package com.bvanseg.just.serialization.codec.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.bvanseg.just.serialization.codec.Codec;
import com.bvanseg.just.serialization.codec.CodecTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CodecsTest {

    @Test
    void constructor_Throws_UnsupportedOperationException() {
        // Act & Assert
        assertThrows(UnsupportedOperationException.class, Codecs::new);
    }

    @ParameterizedTest
    @MethodSource("provideCodecSuccessArguments")
    void codec_Decode_Success(CodecQueryContainer<Object> codecQueryContainer) {
        // Arrange
        var codec = codecQueryContainer.codec;
        var value = codecQueryContainer.testValue;
        var jsonNode = CodecTestUtils.OBJECT_MAPPER.valueToTree(value);

        // Act
        var result = codec.decode(CodecTestUtils.JACKSON_SCHEMA, jsonNode);

        // Assert
        assertTrue(result.isOk());
        assertEquals(value, result.unwrap());
    }

    @ParameterizedTest
    @MethodSource("provideCodecSuccessArguments")
    void codec_Encode_Success(CodecQueryContainer<Object> codecQueryContainer) {
        // Act
        var codec = codecQueryContainer.codec;
        var value = codecQueryContainer.testValue;
        var jsonNode = codec.encode(CodecTestUtils.JACKSON_SCHEMA, value);

        // Assert
        assertTrue(codecQueryContainer.predicate.test(jsonNode));
        assertEquals(value, codecQueryContainer.transformer.apply(jsonNode));
    }

    private record CodecQueryContainer<T>(
        Codec<T> codec,
        T testValue,
        Predicate<JsonNode> predicate,
        Function<JsonNode, T> transformer
    ) {}

    private static Stream<Arguments> provideCodecSuccessArguments() {
        return Stream.of(
            Arguments.of(new CodecQueryContainer<>(Codecs.BOOLEAN, true, JsonNode::isBoolean, JsonNode::asBoolean)),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.BYTE,
                    (byte) 1,
                    JsonNode::isNumber,
                    (jsonNode) -> jsonNode.numberValue().byteValue()
                )
            ),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.SHORT,
                    (short) 1,
                    JsonNode::isNumber,
                    (jsonNode) -> jsonNode.numberValue().shortValue()
                )
            ),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.INT,
                    1,
                    JsonNode::isNumber,
                    (jsonNode) -> jsonNode.numberValue().intValue()
                )
            ),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.FLOAT,
                    1F,
                    JsonNode::isNumber,
                    (jsonNode) -> jsonNode.numberValue().floatValue()
                )
            ),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.LONG,
                    1L,
                    JsonNode::isNumber,
                    (jsonNode) -> jsonNode.numberValue().longValue()
                )
            ),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.DOUBLE,
                    1D,
                    JsonNode::isNumber,
                    (jsonNode) -> jsonNode.numberValue().doubleValue()
                )
            ),
            Arguments.of(
                new CodecQueryContainer<>(Codecs.STRING, "Hello, world!", JsonNode::isTextual, JsonNode::asText)
            ),
            Arguments.of(
                new CodecQueryContainer<>(
                    Codecs.UUID_STRING,
                    UUID.randomUUID(),
                    JsonNode::isTextual,
                    jsonNode -> UUID.fromString(jsonNode.asText())
                )
            )
        );
    }
}
