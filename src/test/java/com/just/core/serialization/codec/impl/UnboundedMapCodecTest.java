package com.just.core.serialization.codec.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Stream;

import com.just.core.functional.tuple.Tuple2;
import com.just.core.serialization.codec.Codec;
import com.just.core.serialization.codec.CodecTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnboundedMapCodecTest {

    private static final Codec<Map<String, Integer>> CODEC = Codec.unboundedMap(Codecs.STRING, Codecs.INT);

    @Test
    void decode_Success() {
        // language=json
        var json = """
            {
              "one": 1,
              "two": 2,
              "three": 3
            }
            """;

        JsonNode node;

        try {
            node = CodecTestUtils.OBJECT_MAPPER.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var result = CODEC.decode(CodecTestUtils.JACKSON_SCHEMA, node);

        assertTrue(result.isOk());

        var map = result.unwrap();

        assertEquals(3, map.size());
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
    }

    @Test
    void encode_Success() {
        var map = Map.of(
            "alpha",
            10,
            "beta",
            20
        );

        var encoded = CODEC.encode(CodecTestUtils.JACKSON_SCHEMA, map);

        assertTrue(encoded.isObject());
        assertEquals(10, encoded.get("alpha").intValue());
        assertEquals(20, encoded.get("beta").intValue());
        assertEquals(2, encoded.size());
    }

    @Test
    void decode_EmptyMap() {
        // Empty JSON object.
        var json = "{}";

        JsonNode node;

        try {
            node = CodecTestUtils.OBJECT_MAPPER.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var result = CODEC.decode(CodecTestUtils.JACKSON_SCHEMA, node);

        assertTrue(result.isOk());

        var map = result.unwrap();

        assertTrue(map.isEmpty(), "Decoded map should be empty.");
    }

    @Test
    void encode_EmptyMap() {
        var emptyMap = Map.<String, Integer>of();

        var encoded = CODEC.encode(CodecTestUtils.JACKSON_SCHEMA, emptyMap);

        assertTrue(encoded.isObject());
        assertEquals(0, encoded.size(), "Encoded object should be empty.");
    }

    @Test
    void encode_FailsOnNonTextualKey() {
        // not a string!
        var badKey = CodecTestUtils.OBJECT_MAPPER.valueToTree(123);
        var value = CodecTestUtils.OBJECT_MAPPER.valueToTree(5);

        var entries = Stream.of(new Tuple2<>(badKey, value));

        assertThrows(IllegalArgumentException.class, () -> CodecTestUtils.JACKSON_SCHEMA.createMap(entries));
    }

    @Test
    void decode_FailsOnInvalidValue() {
        // "abc" is not a valid integer.
        // language=json
        var json = """
            {
              "a": "abc"
            }
            """;

        JsonNode node;

        try {
            node = CodecTestUtils.OBJECT_MAPPER.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var result = CODEC.decode(CodecTestUtils.JACKSON_SCHEMA, node);

        assertTrue(result.isErr(), "Should fail to decode non-integer value.");
    }

    @Test
    void decode_FailsOnNonObject() {
        // This is just a number, not an object.
        var json = "42";

        JsonNode node;

        try {
            node = CodecTestUtils.OBJECT_MAPPER.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var result = CODEC.decode(CodecTestUtils.JACKSON_SCHEMA, node);

        assertTrue(result.isErr(), "Should fail to decode from non-object input.");
    }

}
