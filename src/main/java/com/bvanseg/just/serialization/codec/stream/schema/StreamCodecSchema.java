package com.bvanseg.just.serialization.codec.stream.schema;

import com.bvanseg.just.serialization.codec.stream.StreamCodec;

public interface StreamCodecSchema<T> {

    boolean readBoolean(T input);

    byte readByte(T input);

    byte[] readBytes(T input, int length);

    char readChar(T input);

    short readShort(T input);

    int readVarInt(T input);

    int readInt(T input);

    float readFloat(T input);

    long readLong(T input);

    double readDouble(T input);

    default <T2> T2 read(T input, StreamCodec<T2> codec) {
        return codec.decode(this, input);
    }

    void writeBoolean(T input, boolean value);

    void writeByte(T input, byte value);

    void writeBytes(T input, byte[] value);

    void writeChar(T input, char value);

    void writeShort(T input, short value);

    void writeVarInt(T input, int value);

    void writeInt(T input, int value);

    void writeFloat(T input, float value);

    void writeLong(T input, long value);

    void writeDouble(T input, double value);

    default <T2> void write(T input, StreamCodec<T2> codec, T2 value) {
        codec.encode(this, input, value);
    }
}
