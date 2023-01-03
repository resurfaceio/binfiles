// © 2016-2023 Resurface Labs Inc.

package io.resurface.binfiles;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * String field used in binary message format.
 */
public final class BinaryHttpMessageString extends PersistentHttpMessageString {

    private byte[] buf;
    private int len;
    private int offset;

    /**
     * Sets read buffer for this field and resets internal state.
     */
    public void buffer(byte[] buf) {
        this.buf = buf;
    }

    /**
     * Returns field storage in bytes, including all metadata and with compression.
     */
    public int bytes() {
        return len + 4;
    }

    /**
     * Returns true if string is null.
     */
    public boolean isNull() {
        return len == 0;
    }

    /**
     * Returns decompressed length of this field in bytes.
     */
    public int length() {
        return len;
    }

    /**
     * Returns offset to this field in bytes.
     */
    public int offset() {
        return offset;
    }

    /**
     * Reads string position within current buffer.
     */
    public int read(int offset, ByteBuffer in) {
        this.offset = offset;
        this.len = in.getInt();
        return len;
    }

    /**
     * Reads field from primitive type.
     */
    public void read(String s) {
        if (s == null) {
            offset = 0;
            len = 0;
        } else {
            buf = s.getBytes(StandardCharsets.UTF_8);
            offset = 0;
            len = buf.length;
        }
    }

    /**
     * Returns length of this field in bytes, with any compression applied.
     */
    public int stored() {
        return len;
    }

    /**
     * Returns field as primitive type, or null if field is empty.
     */
    public Slice toSlice() {
        return len == 0 ? null : Slices.wrappedBuffer(buf, offset, len);
    }

    /**
     * Writes string length to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putInt(len);
    }

    /**
     * Writes string contents to in-memory buffer.
     */
    public void writeContents(ByteBuffer out) {
        if (len > 0) out.put(buf);
    }

}
