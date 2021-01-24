// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.charset.StandardCharsets;

/**
 * Field used in binary format for HTTP messages.
 */
public class BinaryHttpMessageField {

    public byte[] buffer;
    public int len;

    public void read(ObjectInput in) throws IOException {
        len = in.readInt();
        if (len > 0) {
            if (buffer == null || buffer.length < len) buffer = new byte[len];
            in.readFully(buffer, 0, len);
        }
    }

    public void set(String s) {
        if (s == null) {
            len = 0;
        } else {
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            len = b.length;
            if (buffer == null || buffer.length < len) buffer = new byte[len];
            System.arraycopy(b, 0, buffer, 0, len);
        }
    }

    public String toString() {
        return len == 0 ? null : new String(buffer, 0, len);
    }

    public void write(ObjectOutput out) throws IOException {
        out.writeInt(len);
        if (len > 0) out.write(buffer, 0, len);
    }

}
