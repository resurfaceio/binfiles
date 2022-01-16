// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * String field used in binary message format.
 */
public final class BinaryHttpMessageString {

    private byte[] buf;
    private int len;
    private int offset;

    /**
     * Returns field size in bytes.
     */
    public int bytes() {
        return len + 4;
    }

    /**
     * Returns internal buffer for this field.
     */
    public byte[] buffer() {
        return buf;
    }

    /**
     * Sets internal buffer for this field.
     */
    public void buffer(byte[] buf) {
        this.buf = buf;
    }

    /**
     * Returns length of this field in bytes.
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
     * Returns field as primitive type, or null if field is empty.
     */
    public String value() {
        return len == 0 ? null : new String(buf, offset, len);
    }

    // SERIALIZATION METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Reads field from object stream. (DEPRECATED)
     */
    public void read(ObjectInputStream in) throws IOException {
        len = in.readInt();
        if (len > 0) {
            if (buf == null || buf.length < len) buf = new byte[len];
            in.readFully(buf, 0, len);
        }
    }

    /**
     * Reads string position within current buffer.
     */
    public int read(int offset, int length) {
        this.offset = offset;
        this.len = length;
        return length;
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
     * Writes string length to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putInt(len);
    }

    /**
     * Writes string contents to in-memory buffer.
     */
    public void write2(ByteBuffer out) {
        if (len > 0) out.put(buf);
    }

}
