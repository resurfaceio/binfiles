// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Integer field used in binary message format.
 */
public final class BinaryHttpMessageInteger {

    private int value;

    /**
     * Returns field length in bytes.
     */
    public int length() {
        return 4;
    }

    /**
     * Returns field as unboxed type.
     */
    public int value() {
        return value;
    }

    /**
     * Reads field from input stream.
     */
    public void read(DataInput in) throws IOException {
        value = in.readInt();
    }

    /**
     * Reads field from unboxed type.
     */
    public void read(int value) {
        this.value = value;
    }

    /**
     * Writes field to output stream.
     */
    public void write(DataOutput out) throws IOException {
        out.writeInt(value);
    }

}
