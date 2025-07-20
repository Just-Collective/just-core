package com.bvanseg.just.serialization.codec.schema;

import java.util.function.Consumer;
import java.util.stream.Stream;

import com.bvanseg.just.functional.result.Result;

public interface CodecSchema<T> {

    T empty();

    Result<Boolean, T> getBooleanValue(T input);

    Result<Number, T> getNumberValue(T input);

    Result<String, T> getStringValue(T input);

    Result<Stream<T>, T> getStream(T input);

    T createBooleanValue(boolean value);

    T createNumber(Number value);

    T createStringValue(String value);

    T createList(Stream<T> elements);

    default Result<Consumer<Consumer<T>>, T> getList(T input) {
        return getStream(input).map(stream -> stream::forEach);
    }

    default T createByteValue(byte value) {
        return createNumber(value);
    }

    default T createShortValue(short value) {
        return createNumber(value);
    }

    default T createIntValue(int value) {
        return createNumber(value);
    }

    default T createFloatValue(float value) {
        return createNumber(value);
    }

    default T createLongValue(long value) {
        return createNumber(value);
    }

    default T createDoubleValue(double value) {
        return createNumber(value);
    }
}
