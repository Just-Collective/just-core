package com.bvanseg.just.serialization.codec;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.bvanseg.just.serialization.codec.stream.schema.impl.ByteBufferStreamCodecSchema;

public class CodecTestUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final ByteBufferStreamCodecSchema BYTE_BUFFER_SCHEMA = new ByteBufferStreamCodecSchema();

    public static final JacksonCodecSchema JACKSON_SCHEMA = new JacksonCodecSchema(OBJECT_MAPPER);

}
