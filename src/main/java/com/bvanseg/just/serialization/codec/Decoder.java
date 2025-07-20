package com.bvanseg.just.serialization.codec;

import com.bvanseg.just.functional.result.Result;
import com.bvanseg.just.serialization.codec.schema.CodecSchema;

public interface Decoder<A> {

    <T> Result<A, T> decode(CodecSchema<T> codecSchema, T input);
}
