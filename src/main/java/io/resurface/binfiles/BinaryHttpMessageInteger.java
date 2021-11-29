// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

/**
 * Integer field used in binary message format.
 */
public final class BinaryHttpMessageInteger {

    private int value;

    /**
     * Returns field size in bytes.
     */
    public int bytes() {
        return 4;
    }

    /**
     * Returns field as primitive type.
     */
    public int value() {
        return value;
    }

    // SERIALIZATION METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Reads field from object stream. (DEPRECATED)
     */
    public void read(ObjectInputStream in) throws IOException {
        value = in.readInt();
    }

    /**
     * Reads field from primitive type.
     */
    public void read(int value) {
        this.value = value;
    }

    /**
     * Writes field to in-memory buffer.
     */
    public void write(ByteBuffer out) {
        out.putInt(value);
    }

}
