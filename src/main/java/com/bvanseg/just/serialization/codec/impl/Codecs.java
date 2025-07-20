package com.bvanseg.just.serialization.codec.impl;

import java.util.UUID;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.Codec;
import com.bvanseg.just.serialization.codec.Decoder;
import com.bvanseg.just.serialization.codec.Encoder;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public class Codecs {

    public static final Codec<Boolean> BOOLEAN = Codec.of("Boolean", new Decoder<>() {

        @Override
        public <T> Result<Boolean, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getBooleanValue(input);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Boolean value) {
            return codecSchema.createBooleanValue(value);
        }
    });

    public static final Codec<Byte> BYTE = Codec.of("Byte", new Decoder<>() {

        @Override
        public <T> Result<Byte, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::byteValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Byte value) {
            return codecSchema.createByteValue(value);
        }
    });

    public static final Codec<Short> SHORT = Codec.of("Short", new Decoder<>() {

        @Override
        public <T> Result<Short, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::shortValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Short value) {
            return codecSchema.createShortValue(value);
        }
    });

    public static final Codec<Integer> INT = Codec.of("Int", new Decoder<>() {

        @Override
        public <T> Result<Integer, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::intValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Integer value) {
            return codecSchema.createIntValue(value);
        }
    });

    public static final Codec<Float> FLOAT = Codec.of("Float", new Decoder<>() {

        @Override
        public <T> Result<Float, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::floatValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Float value) {
            return codecSchema.createFloatValue(value);
        }
    });

    public static final Codec<Long> LONG = Codec.of("Long", new Decoder<>() {

        @Override
        public <T> Result<Long, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::longValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Long value) {
            return codecSchema.createLongValue(value);
        }
    });

    public static final Codec<Double> DOUBLE = Codec.of("Double", new Decoder<>() {

        @Override
        public <T> Result<Double, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getNumberValue(input)
                .map(Number::doubleValue);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, Double value) {
            return codecSchema.createDoubleValue(value);
        }
    });

    public static final Codec<String> STRING = Codec.of("String", new Decoder<>() {

        @Override
        public <T> Result<String, T> decode(CodecSchema<T> codecSchema, T input) {
            return codecSchema.getStringValue(input);
        }
    }, new Encoder<>() {

        @Override
        public <T> T encode(CodecSchema<T> codecSchema, String value) {
            return codecSchema.createStringValue(value);
        }
    });

    public static final Codec<UUID> UUID = new UUIDCodec();

    public static final Codec<UUID> UUID_STRING = STRING.contraMapFlatMap(
        java.util.UUID::fromString,
        java.util.UUID::toString
    );

    Codecs() {
        throw new UnsupportedOperationException();
    }
}
