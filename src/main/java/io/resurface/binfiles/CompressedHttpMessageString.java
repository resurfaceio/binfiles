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
 * String field with automatic compression, used in binary message format.
 */
public final class CompressedHttpMessageString {

    private byte[] buf;
    private final LZ4Compressor compressor;
    private final LZ4FastDecompressor decompressor;
    private int len;
    private int offset;
    private int stored;
    private final byte[] temp;
    private final int threshold;

    /**
     * Constructor with external message buffer and default compression threshold.
     */
    public CompressedHttpMessageString(byte[] buf) {
        this(buf, 256);  // todo is this the right default?
    }

    /**
     * Constructor with external message buffer and custom compression threshold.
     */
    public CompressedHttpMessageString(byte[] buf, int threshold) {
        this.buf = buf;
        this.compressor = LZ4Factory.fastestInstance().fastCompressor();
        this.decompressor = LZ4Factory.fastestInstance().fastDecompressor();
        this.temp = new byte[compressor.maxCompressedLength(buf.length)];
        this.threshold = threshold;
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
     * Returns field as slice, or null if field is empty.
     */
    public Slice value() {
        if (len == 0) {
            return null;
        } else if (len < threshold) {
            return Slices.wrappedBuffer(buf, offset, len);
        } else {
            decompressor.decompress(buf, offset, temp, 0, len);
            return Slices.wrappedBuffer(temp, 0, len);  // todo throw exception if wrong number of bytes after decompression?
        }
    }

    // SERIALIZATION METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Reads string position within external message buffer.
     */
    public int read(int offset, int length, int stored) {
        // todo check values for out of range?
        this.offset = offset;
        this.len = length;
        this.stored = stored;
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
