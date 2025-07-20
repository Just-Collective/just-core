package com.bvanseg.just.serialization.codec.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.StreamSupport;

import com.bvanseg.just.serialization.codec.Codec;
import com.bvanseg.just.serialization.codec.CodecTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCodecTest {

    private static final Codec<List<Integer>> CODEC = Codec.list(Codecs.INT);

    private static final List<Integer> INTEGERS = List.of(1, 2, 3, 4, 5);

    @Test
    void decode_Success() {
        // Arrange
        var arrayNode = CodecTestUtils.OBJECT_MAPPER.createArrayNode();
        INTEGERS.forEach(arrayNode::add);

        // Act
        var result = CODEC.decode(CodecTestUtils.JACKSON_SCHEMA, arrayNode);

        // Assert
        assertTrue(result.isOk());

        var resultNumbers = result.unwrap();

        assertEquals(INTEGERS, resultNumbers);
    }

    @Test
    void encode_Success() {
        // Act
        var jsonNode = CODEC.encode(CodecTestUtils.JACKSON_SCHEMA, INTEGERS);

        // Assert
        assertTrue(jsonNode.isArray());

        var arrayNode = (ArrayNode) jsonNode;
        var arrayNodeNumbers = StreamSupport.stream(arrayNode.spliterator(), false).map(JsonNode::asInt).toList();

        assertEquals(INTEGERS.size(), arrayNode.size());
        assertEquals(INTEGERS, arrayNodeNumbers);
    }
}
