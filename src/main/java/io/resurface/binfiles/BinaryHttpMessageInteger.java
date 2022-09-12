// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import java.nio.ByteBuffer;

/**
 * Integer field used in binary message format.
 */
public final class BinaryHttpMessageInteger {

    private int value;

    /**
     * Returns field size in bytes, including all metadata.
     */
    public int bytes() {
        return 4;
    }

    /**
     * Reads field from primitive type.
     */
    public void read(int value) {
        this.value = value;
    }

    /**
     * Returns field as primitive type.
     */
    public int value() {
        return value;
    }

    /**
     * Writes field to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putInt(value);
    }

}
