package com.bvanseg.just.serialization.codec;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CodecTestUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final JacksonCodecSchema JACKSON_SCHEMA = new JacksonCodecSchema(OBJECT_MAPPER);

}
