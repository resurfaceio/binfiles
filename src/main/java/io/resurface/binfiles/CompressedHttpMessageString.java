// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * String field used in compressed message format.
 */
public final class CompressedHttpMessageString extends PersistentHttpMessageString {

    private final LZ4Compressor compressor;
    private final LZ4FastDecompressor decompressor;
    private final int threshold;

    private byte[] buf;
    private int len;
    private int offset;
    private int stored;
    private byte[] temp;

    /**
     * Constructor with default compression threshold.
     */
    public CompressedHttpMessageString() {
        this(256);
    }

    /**
     * Constructor with custom compression threshold.
     */
    public CompressedHttpMessageString(int threshold) {
        this.compressor = LZ4Factory.fastestInstance().highCompressor();
        this.decompressor = LZ4Factory.fastestInstance().fastDecompressor();
        this.threshold = threshold;
    }

    /**
     * Sets read buffer for this field and resets internal state.
     */
    public void buffer(byte[] buf) {
        this.buf = buf;
        this.len = 0;
        this.offset = 0;
        this.stored = 0;
    }

    /**
     * Returns field storage in bytes, including all metadata and with compression.
     */
    public int bytes() {
        return stored + 8;
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
    public int offset() {  // todo add test coverage
        return offset;
    }

    /**
     * Returns length of this field in bytes, with any compression applied.
     */
    public int stored() {  // todo add test coverage
        return stored;
    }

    /**
     * Returns field as slice, or null if field is empty.
     */
    public Slice toSlice() {
        if (buf == null || len == 0) {
            return null;
        } else if (len < threshold) {
            return Slices.wrappedBuffer(buf, offset, len);
        } else {
            if (temp == null || temp.length < len) temp = new byte[len];
            decompressor.decompress(buf, offset, temp, 0, len);
            return Slices.wrappedBuffer(temp, 0, len);  // todo throw exception if wrong number of bytes after decompression?
        }
    }

    /**
     * Reads string position within external message buffer.
     */
    public int read(int offset, ByteBuffer in) {
        // todo check values for out of range?
        this.offset = offset;
        this.len = in.getInt();
        this.stored = in.getInt();
        return stored;
    }

    /**
     * Reads field from primitive type.
     */
    public void read(String s) {
        if (s == null) {
            offset = 0;
            len = 0;
            stored = 0;
        } else {
            buf = s.getBytes(StandardCharsets.UTF_8);
            offset = 0;
            len = buf.length;
            if (len < threshold) {
                stored = buf.length;
            } else {
                int max_length = compressor.maxCompressedLength(buf.length);
                if (temp == null || temp.length < max_length) temp = new byte[max_length];
                stored = compressor.compress(buf, 0, len, temp, 0, temp.length);
                buf = temp;
            }
        }
    }

    /**
     * Writes string metadata to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putInt(len);
        out.putInt(stored);
    }

    /**
     * Writes string contents to in-memory buffer.
     */
    public void write2(ByteBuffer out) {
        if (len > 0) out.put(buf, offset, stored);
    }

}
