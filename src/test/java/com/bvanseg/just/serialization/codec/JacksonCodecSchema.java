package com.bvanseg.just.serialization.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.bvanseg.just.functional.result.Result;
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
}
