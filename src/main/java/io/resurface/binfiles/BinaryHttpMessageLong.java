// © 2016-2023 Resurface Labs Inc.

package io.resurface.binfiles;

import java.nio.ByteBuffer;

/**
 * Long field used in binary message format.
 */
public final class BinaryHttpMessageLong {

    private long value;

    /**
     * Returns field size in bytes, including all metadata.
     */
    public int bytes() {
        return 8;
    }

    /**
     * Reads field from primitive type.
     */
    public void read(long value) {
        this.value = value;
    }

    /**
     * Returns field as primitive type.
     */
    public long value() {
        return value;
    }

    /**
     * Writes field to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putLong(value);
    }

}
