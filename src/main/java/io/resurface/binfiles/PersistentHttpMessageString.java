// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import io.airlift.slice.Slice;

import java.nio.ByteBuffer;

/**
 * Abstract string field used in binary message formats.
 */
public abstract class PersistentHttpMessageString {

    /**
     * Sets read buffer for this field and resets internal state.
     */
    public abstract void buffer(byte[] buf);

    /**
     * Returns field storage in bytes, including all metadata and with compression.
     */
    public abstract int bytes();

    /**
     * Returns decompressed length of this field in bytes.
     */
    public abstract int length();

    /**
     * Returns offset to this field in bytes.
     */
    public abstract int offset();

    /**
     * Reads string position within current buffer.
     */
    public abstract int read(int offset, ByteBuffer in);

    /**
     * Reads field from primitive type.
     */
    public abstract void read(String s);

    /**
     * Returns length of this field in bytes, with any compression applied.
     */
    public abstract int stored();

    /**
     * Returns field as slice, or null if field is empty.
     */
    public abstract Slice toSlice();

    /**
     * Returns field as literal String, or null if field is empty.
     */
    public String value() {
        return (length() == 0) ? null : toSlice().toStringUtf8();
    }

    /**
     * Writes string length to in-memory buffer.
     */
    public abstract void write(ByteBuffer out);

    /**
     * Writes string contents to in-memory buffer.
     */
    public abstract void write2(ByteBuffer out);

}
