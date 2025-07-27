package com.bvanseg.just.serialization.codec.stream.schema.impl;

import java.nio.ByteBuffer;

import com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema;

public class ByteBufferStreamCodecSchema implements StreamCodecSchema<ByteBuffer> {

    @Override
    public byte readByte(ByteBuffer input) {
        return input.get();
    }

    @Override
    public byte[] readBytes(ByteBuffer input, int length) {
        var bytes = new byte[length];
        // Reads 'length' bytes and advances the position.
        input.get(bytes);
        return bytes;
    }

    @Override
    public char readChar(ByteBuffer input) {
        return input.getChar();
    }

    @Override
    public short readShort(ByteBuffer input) {
        return input.getShort();
    }

    @Override
    public int readInt(ByteBuffer input) {
        return input.getInt();
    }

    @Override
    public float readFloat(ByteBuffer input) {
        return input.getFloat();
    }

    @Override
    public long readLong(ByteBuffer input) {
        return input.getLong();
    }

    @Override
    public double readDouble(ByteBuffer input) {
        return input.getDouble();
    }

    @Override
    public void writeBoolean(ByteBuffer input, boolean value) {
        input.put((byte) (value ? 1 : 0));
    }

    @Override
    public void writeByte(ByteBuffer input, byte value) {
        input.put(value);
    }

    @Override
    public void writeBytes(ByteBuffer input, byte[] value) {
        input.put(value);
    }

    @Override
    public void writeChar(ByteBuffer input, char value) {
        input.putChar(value);
    }

    @Override
    public void writeShort(ByteBuffer input, short value) {
        input.putShort(value);
    }

    @Override
    public void writeInt(ByteBuffer input, int value) {
        input.putInt(value);
    }

    @Override
    public void writeFloat(ByteBuffer input, float value) {
        input.putFloat(value);
    }

    @Override
    public void writeLong(ByteBuffer input, long value) {
        input.putLong(value);
    }

    @Override
    public void writeDouble(ByteBuffer input, double value) {
        input.putDouble(value);
    }
}
