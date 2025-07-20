package com.bvanseg.just.serialization.codec;

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

    static <K, V> UnboundedMapCodec<K, V> unboundedMap(final Codec<K> keyCodec, final Codec<V> elementCodec) {
        return new UnboundedMapCodec<>(keyCodec, elementCodec);
    }

    default ListCodec<A> asList() {
        return list(this);
    }
}
