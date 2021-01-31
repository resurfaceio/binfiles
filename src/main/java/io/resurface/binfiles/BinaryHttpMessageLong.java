// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Long field used in binary message format.
 */
public final class BinaryHttpMessageLong {

    private long value;

    /**
     * Returns field length in bytes.
     */
    public int length() {
        return 8;
    }

    /**
     * Returns field as unboxed type.
     */
    public long value() {
        return value;
    }

    /**
     * Reads field from input stream.
     */
    public void read(ObjectInput in) throws IOException {
        value = in.readLong();
    }

    /**
     * Reads field from unboxed type.
     */
    public void read(long value) {
        this.value = value;
    }

    /**
     * Writes field to output stream.
     */
    public void write(ObjectOutput out) throws IOException {
        out.writeLong(value);
    }

}