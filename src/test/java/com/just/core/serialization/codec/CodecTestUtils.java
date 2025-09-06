package com.just.core.serialization.codec;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.just.core.serialization.codec.stream.schema.impl.ByteBufferStreamCodecSchema;

public class CodecTestUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final ByteBufferStreamCodecSchema BYTE_BUFFER_SCHEMA = new ByteBufferStreamCodecSchema();

    public static final JacksonCodecSchema JACKSON_SCHEMA = new JacksonCodecSchema(OBJECT_MAPPER);

}
