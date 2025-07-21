package com.bvanseg.just.serialization.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.functional.tuple.Tuple2;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public class JacksonCodecSchema implements CodecSchema<JsonNode> {

    private final ObjectMapper objectMapper;

    public JacksonCodecSchema(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public JsonNode empty() {
        return objectMapper.nullNode();
    }

    @Override
    public Result<Boolean, JsonNode> getBooleanValue(JsonNode input) {
        return input.isBoolean()
            ? Result.ok(input.booleanValue())
            : Result.err(input);
    }

    @Override
    public Result<Number, JsonNode> getNumberValue(JsonNode input) {
        return input.isNumber()
            ? Result.ok(input.numberValue())
            : Result.err(input);
    }

    @Override
    public Result<String, JsonNode> getStringValue(JsonNode input) {
        return input.isTextual()
            ? Result.ok(input.asText())
            : Result.err(input);
    }

    @Override
    public Result<Stream<JsonNode>, JsonNode> getStream(JsonNode input) {
        if (input.isArray()) {
            return Result.ok(
                StreamSupport.stream(input.spliterator(), false)
                    .map(node -> node.isNull() ? null : node)
            );
        }

        return Result.err(input);
    }

    @Override
    public Result<Stream<Tuple2<JsonNode, JsonNode>>, JsonNode> getMap(JsonNode input) {
        if (!input.isObject()) {
            return Result.err(input);
        }

        // Iterator<Map.Entry<String, JsonNode>>.
        var fields = input.fields();
        var stream = StreamSupport.stream(((Iterable<Map.Entry<String, JsonNode>>) () -> fields).spliterator(), false)
            .map(entry -> new Tuple2<>(objectMapper.convertValue(entry.getKey(), JsonNode.class), entry.getValue()));

        return Result.ok(stream);
    }

    @Override
    public JsonNode createBooleanValue(boolean value) {
        return objectMapper.valueToTree(value);
    }

    @Override
    public JsonNode createNumber(Number value) {
        return objectMapper.valueToTree(value);
    }

    @Override
    public JsonNode createStringValue(String value) {
        return objectMapper.valueToTree(value);
    }

    @Override
    public JsonNode createList(Stream<JsonNode> elements) {
        var arrayNode = objectMapper.createArrayNode();
        elements.forEach(arrayNode::add);
        return arrayNode;
    }

    @Override
    public JsonNode createMap(Stream<Tuple2<JsonNode, JsonNode>> entries) {
        var objectNode = objectMapper.createObjectNode();

        entries.forEach(pair -> {
            var keyNode = pair.v1();
            var valueNode = pair.v2();

            if (!keyNode.isTextual()) {
                throw new IllegalArgumentException("JacksonCodecSchema map keys must be textual (string) values.");
            }

            String key = keyNode.textValue();

            objectNode.set(
                key,
                valueNode == null
                    ? objectMapper.nullNode()
                    : valueNode
            );
        });

        return objectNode;
    }
}
