// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

/**
 * Long field used in binary message format.
 */
public final class BinaryHttpMessageLong {

    private long value;

    /**
     * Returns field size in bytes.
     */
    public int bytes() {
        return 8;
    }

    /**
     * Returns field as primitive type.
     */
    public long value() {
        return value;
    }

    // SERIALIZATION METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Reads field from object stream. (DEPRECATED)
     */
    public void read(ObjectInputStream in) throws IOException {
        value = in.readLong();
    }

    /**
     * Reads field from primitive type.
     */
    public void read(long value) {
        this.value = value;
    }

    /**
     * Writes field to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putLong(value);
    }

}
