// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * String field used in compressed message format, backed by hashing dictionary.
 */
public final class HashedHttpMessageString extends PersistentHttpMessageString {

    private byte[] buf;
    private final HashedDictionary dictionary;
    private int hash;
    private int len;
    private int offset;

    /**
     * Default constructor with hashing dictionary.
     */
    public HashedHttpMessageString(HashedDictionary dictionary) {
        this.dictionary = dictionary;
    }

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
        int i = in.getInt();
        if (i < 0) {
            this.hash = i;
            this.len = 0;
        } else {
            this.hash = 0;
            this.len = i;
        }
        return len;
    }

    /**
     * Reads field from primitive type.
     */
    public void read(String s) {
        offset = 0;
        if (s == null) {
            buf = null;
            hash = 0;
            len = 0;
        } else {
            Integer h = dictionary.values.get(s);
            if (h == null) {
                buf = s.getBytes(StandardCharsets.UTF_8);
                hash = 0;
                len = buf.length;
            } else {
                buf = null;
                hash = h;
                len = 0;
            }
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
        if (hash != 0) {
            return dictionary.slices.get(hash);
        } else if (len == 0) {
            return null;
        } else {
            return Slices.wrappedBuffer(buf, offset, len);
        }
    }

    /**
     * Returns field as literal String, or null if field is empty.
     */
    public String value() {
        Slice slice = toSlice();
        return (slice == null) ? null : slice.toStringUtf8();
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
