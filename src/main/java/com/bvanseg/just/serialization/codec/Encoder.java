package com.bvanseg.just.serialization.codec;

import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public interface Encoder<A> {

    <T> T encode(CodecSchema<T> codecSchema, A value);
}
