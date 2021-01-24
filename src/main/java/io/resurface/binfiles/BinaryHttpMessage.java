// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Binary format for HTTP messages.
 */
public class BinaryHttpMessage {

    public final BinaryHttpMessageField id = new BinaryHttpMessageField();
    public final BinaryHttpMessageField agent_category = new BinaryHttpMessageField();
    public final BinaryHttpMessageField agent_device = new BinaryHttpMessageField();
    public final BinaryHttpMessageField agent_name = new BinaryHttpMessageField();
    public final BinaryHttpMessageField host = new BinaryHttpMessageField();
    public final BinaryHttpMessageField interval_category = new BinaryHttpMessageField();
    public final BinaryHttpMessageField interval_clique = new BinaryHttpMessageField();
    public long interval_millis;
    public final BinaryHttpMessageField request_body = new BinaryHttpMessageField();
    public final BinaryHttpMessageField request_content_type = new BinaryHttpMessageField();
    public final BinaryHttpMessageField request_headers = new BinaryHttpMessageField();
    public final BinaryHttpMessageField request_method = new BinaryHttpMessageField();
    public final BinaryHttpMessageField request_params = new BinaryHttpMessageField();
    public final BinaryHttpMessageField request_url = new BinaryHttpMessageField();
    public final BinaryHttpMessageField request_user_agent = new BinaryHttpMessageField();
    public final BinaryHttpMessageField response_body = new BinaryHttpMessageField();
    public final BinaryHttpMessageField response_code = new BinaryHttpMessageField();
    public final BinaryHttpMessageField response_content_type = new BinaryHttpMessageField();
    public final BinaryHttpMessageField response_headers = new BinaryHttpMessageField();
    public long response_time_millis;
    public final BinaryHttpMessageField size_category = new BinaryHttpMessageField();
    public int size_request_bytes;
    public int size_response_bytes;

    /**
     * Returns the length of the current message in bytes.
     */
    public int length() {
        int result = 0;
        result += id.len;
        result += agent_category.len;
        result += agent_device.len;
        result += agent_name.len;
        result += host.len;
        result += interval_category.len;
        result += interval_clique.len;
        result += 8; // interval_millis
        result += request_body.len;
        result += request_content_type.len;
        result += request_headers.len;
        result += request_method.len;
        result += request_params.len;
        result += request_url.len;
        result += request_user_agent.len;
        result += response_body.len;
        result += response_code.len;
        result += response_content_type.len;
        result += response_headers.len;
        result += 8; // response_time_millis
        result += size_category.len;
        result += 4; // size_request_bytes
        result += 4; // size_response_bytes
        return result;
    }

    /**
     * Reads all message fields from input stream.
     */
    public void read(ObjectInput in) throws IOException {
        id.read(in);
        agent_category.read(in);
        agent_device.read(in);
        agent_name.read(in);
        host.read(in);
        interval_category.read(in);
        interval_clique.read(in);
        interval_millis = in.readLong();
        request_body.read(in);
        request_content_type.read(in);
        request_headers.read(in);
        request_method.read(in);
        request_params.read(in);
        request_url.read(in);
        request_user_agent.read(in);
        response_body.read(in);
        response_code.read(in);
        response_content_type.read(in);
        response_headers.read(in);
        response_time_millis = in.readLong();
        size_category.read(in);
        size_request_bytes = in.readInt();
        size_response_bytes = in.readInt();
    }

    /**
     * Writes all message fields to output stream.
     */
    public void write(ObjectOutput out) throws IOException {
        id.write(out);
        agent_category.write(out);
        agent_device.write(out);
        agent_name.write(out);
        host.write(out);
        interval_category.write(out);
        interval_clique.write(out);
        out.writeLong(interval_millis);
        request_body.write(out);
        request_content_type.write(out);
        request_headers.write(out);
        request_method.write(out);
        request_params.write(out);
        request_url.write(out);
        request_user_agent.write(out);
        response_body.write(out);
        response_code.write(out);
        response_content_type.write(out);
        response_headers.write(out);
        out.writeLong(response_time_millis);
        size_category.write(out);
        out.writeInt(size_request_bytes);
        out.writeInt(size_response_bytes);
    }

}
