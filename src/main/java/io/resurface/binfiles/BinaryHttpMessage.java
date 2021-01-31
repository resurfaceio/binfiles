// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Binary format for HTTP messages.
 */
public final class BinaryHttpMessage {

    public final BinaryHttpMessageString id = new BinaryHttpMessageString();
    public final BinaryHttpMessageString agent_category = new BinaryHttpMessageString();
    public final BinaryHttpMessageString agent_device = new BinaryHttpMessageString();
    public final BinaryHttpMessageString agent_name = new BinaryHttpMessageString();
    public final BinaryHttpMessageString host = new BinaryHttpMessageString();
    public final BinaryHttpMessageString interval_category = new BinaryHttpMessageString();
    public final BinaryHttpMessageString interval_clique = new BinaryHttpMessageString();
    public final BinaryHttpMessageLong interval_millis = new BinaryHttpMessageLong();
    public final BinaryHttpMessageString request_body = new BinaryHttpMessageString();
    public final BinaryHttpMessageString request_content_type = new BinaryHttpMessageString();
    public final BinaryHttpMessageString request_headers = new BinaryHttpMessageString();
    public final BinaryHttpMessageString request_method = new BinaryHttpMessageString();
    public final BinaryHttpMessageString request_params = new BinaryHttpMessageString();
    public final BinaryHttpMessageString request_url = new BinaryHttpMessageString();
    public final BinaryHttpMessageString request_user_agent = new BinaryHttpMessageString();
    public final BinaryHttpMessageString response_body = new BinaryHttpMessageString();
    public final BinaryHttpMessageString response_code = new BinaryHttpMessageString();
    public final BinaryHttpMessageString response_content_type = new BinaryHttpMessageString();
    public final BinaryHttpMessageString response_headers = new BinaryHttpMessageString();
    public final BinaryHttpMessageLong response_time_millis = new BinaryHttpMessageLong();
    public final BinaryHttpMessageString size_category = new BinaryHttpMessageString();
    public final BinaryHttpMessageInteger size_request_bytes = new BinaryHttpMessageInteger();
    public final BinaryHttpMessageInteger size_response_bytes = new BinaryHttpMessageInteger();

    /**
     * Returns the length of this message in bytes.
     */
    public int length() {
        int result = id.length();
        result += agent_category.length();
        result += agent_device.length();
        result += agent_name.length();
        result += host.length();
        result += interval_category.length();
        result += interval_clique.length();
        result += interval_millis.length();
        result += request_body.length();
        result += request_content_type.length();
        result += request_headers.length();
        result += request_method.length();
        result += request_params.length();
        result += request_url.length();
        result += request_user_agent.length();
        result += response_body.length();
        result += response_code.length();
        result += response_content_type.length();
        result += response_headers.length();
        result += response_time_millis.length();
        result += size_category.length();
        result += size_request_bytes.length();
        result += size_response_bytes.length();
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
        interval_millis.read(in);
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
        response_time_millis.read(in);
        size_category.read(in);
        size_request_bytes.read(in);
        size_response_bytes.read(in);
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
        interval_millis.write(out);
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
        response_time_millis.write(out);
        size_category.write(out);
        size_request_bytes.write(out);
        size_response_bytes.write(out);
    }

}
