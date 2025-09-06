package com.just.core.serialization.codec;

import java.util.Optional;

import com.just.core.functional.function.Function;
import com.just.core.functional.option.Option;
import com.just.core.functional.result.Result;
import com.just.core.serialization.codec.impl.ListCodec;
import com.just.core.serialization.codec.impl.UnboundedMapCodec;
import com.just.core.serialization.codec.schema.CodecSchema;

public interface Codec<A> extends Encoder<A>, Decoder<A> {

    static <A> Codec<A> of(String name, Decoder<A> decoder, Encoder<A> encoder) {
        return new Codec<>() {

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, A value) {
                return encoder.encode(codecSchema, value);
            }

            @Override
            public <T> Result<A, T> decode(CodecSchema<T> codecSchema, T input) {
                return decoder.decode(codecSchema, input);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

    static <A> ListCodec<A> list(Codec<A> elementCodec) {
        return new ListCodec<>(elementCodec);
    }

    static <K, V> UnboundedMapCodec<K, V> unboundedMap(Codec<K> keyCodec, Codec<V> elementCodec) {
        return new UnboundedMapCodec<>(keyCodec, elementCodec);
    }

    default <S> Codec<S> xmap(Function<? super A, ? extends S> to, Function<? super S, ? extends A> from) {
        return Codec.of(this + "[xmapped]", map(to), contraMap(from));
    }

    default <S> Codec<S> contraMapFlatMap(Function<? super A, ? extends S> to, Function<? super S, ? extends A> from) {
        return Codec.of(this + "[contraMapFlatMapped]", flatMap(to), contraMap(from));
    }

    default Codec<Option<A>> asOption() {
        return new Codec<>() {

            @Override
            public <T> Result<Option<A>, T> decode(CodecSchema<T> codecSchema, T input) {
                if (input == null) {
                    return Result.ok(Option.none());
                }

                var result = Codec.this.decode(codecSchema, input);

                return result.isErr()
                    ? Result.ok(Option.none())
                    : result.map(Option::ofNullable);
            }

            @Override
            public <T> T encode(CodecSchema<T> codecSchema, Option<A> value) {
                if (value.isNone()) {
                    return null;
                }

                return Codec.this.encode(codecSchema, value.unwrap());
            }
        };
    }

    default Codec<Optional<A>> asOptional() {
        return asOption().xmap(
            Option::toOptional,
            optional -> optional.isEmpty() ? Option.none() : Option.some(optional.get())
        );
    }

    default ListCodec<A> asList() {
        return list(this);
    }

    default <S> FieldCodec<S, A> fieldOf(String fieldName, Function<S, A> getter) {
        return new FieldCodec<>(fieldName, getter, this);
    }
}
