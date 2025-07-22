package com.bvanseg.just.serialization.codec.schema;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.functional.tuple.Tuple2;

public interface CodecSchema<T> {

    T empty();

    default T emptyMap() {
        return createMap(Stream.of());
    }

    Result<Boolean, T> getBooleanValue(T input);

    Result<Number, T> getNumberValue(T input);

    Result<String, T> getStringValue(T input);

    Result<Stream<T>, T> getStream(T input);

    default Result<IntStream, T> getIntStream(final T input) {
        return getStream(input).andThen(stream -> {
            var list = stream.toList();

            if (list.stream().allMatch(element -> getNumberValue(element).isOk())) {
                return Result.ok(list.stream().mapToInt(element -> getNumberValue(element).unwrap().intValue()));
            }

            return Result.err(input);
        });
    }

    default Result<Consumer<Consumer<T>>, T> getList(T input) {
        return getStream(input).map(stream -> stream::forEach);
    }

    Result<Stream<Tuple2<T, T>>, T> getMap(T input);

    // Retrieves a field from a map-like object.
    Result<T, T> getField(T map, String key);

    T createBooleanValue(boolean value);

    T createNumber(Number value);

    T createStringValue(String value);

    T createList(Stream<T> elements);

    T createMap(Stream<Tuple2<T, T>> entries);

    T createField(T map, String key, T value);

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

    default T createIntList(final IntStream input) {
        return createList(input.mapToObj(this::createIntValue));
    }
}
