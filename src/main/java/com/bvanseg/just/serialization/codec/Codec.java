package com.bvanseg.just.serialization.codec;

import java.util.function.Function;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.impl.ListCodec;
import com.bvanseg.just.serialization.codec.impl.UnboundedMapCodec;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

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

    default ListCodec<A> asList() {
        return list(this);
    }

}
