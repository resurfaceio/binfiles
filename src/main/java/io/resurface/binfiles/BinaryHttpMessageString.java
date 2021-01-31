// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.charset.StandardCharsets;

/**
 * String field used in binary message format.
 */
public final class BinaryHttpMessageString {

    private byte[] buf;
    private int len;

    /**
     * Returns internal buffer for this field.
     */
    public byte[] buffer() {
        return buf;
    }

    /**
     * Returns field length in bytes.
     */
    public int length() {
        return len;
    }

    /**
     * Returns field as unboxed type, or null if field is empty.
     */
    public String value() {
        return len == 0 ? null : new String(buf, 0, len);
    }

    /**
     * Reads field from input stream.
     */
    public void read(ObjectInput in) throws IOException {
        len = in.readInt();
        if (len > 0) {
            if (buf == null || buf.length < len) buf = new byte[len];
            in.readFully(buf, 0, len);
        }
    }

    /**
     * Reads field from unboxed type.
     */
    public void read(String s) {
        if (s == null) {
            len = 0;
        } else {
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            len = b.length;
            if (buf == null || buf.length < len) buf = new byte[len];
            System.arraycopy(b, 0, buf, 0, len);
        }
    }

    /**
     * Writes field to output stream.
     */
    public void write(ObjectOutput out) throws IOException {
        out.writeInt(len);
        if (len > 0) out.write(buf, 0, len);
    }

}
