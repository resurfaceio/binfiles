// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Binary format for HTTP messages.
 */
public class BinaryHttpMessage {

    public byte[] id;
    public byte[] agent_category;
    public byte[] agent_device;
    public byte[] agent_name;
    public byte[] host;
    public byte[] interval_category;
    public byte[] interval_clique;
    public long interval_millis;
    public byte[] request_body;
    public byte[] request_content_type;
    public byte[] request_headers;
    public byte[] request_method;
    public byte[] request_params;
    public byte[] request_url;
    public byte[] request_user_agent;
    public byte[] response_body;
    public byte[] response_code;
    public byte[] response_content_type;
    public byte[] response_headers;
    public long response_time_millis;
    public byte[] size_category;
    public int size_request;
    public int size_response;

    /**
     * Reads all message fields from input stream.
     */
    public void read(ObjectInput in) throws IOException {
        id = readBytes(in);
        agent_category = readBytes(in);
        agent_device = readBytes(in);
        agent_name = readBytes(in);
        host = readBytes(in);
        interval_category = readBytes(in);
        interval_clique = readBytes(in);
        interval_millis = in.readLong();
        request_body = readBytes(in);
        request_content_type = readBytes(in);
        request_headers = readBytes(in);
        request_method = readBytes(in);
        request_params = readBytes(in);
        request_url = readBytes(in);
        request_user_agent = readBytes(in);
        response_body = readBytes(in);
        response_code = readBytes(in);
        response_content_type = readBytes(in);
        response_headers = readBytes(in);
        response_time_millis = in.readLong();
        size_category = readBytes(in);
        size_request = in.readInt();
        size_response = in.readInt();
    }

    /**
     * Writes all message fields to output stream.
     */
    public void write(ObjectOutput out) throws IOException {
        writeBytes(out, id);
        writeBytes(out, agent_category);
        writeBytes(out, agent_device);
        writeBytes(out, agent_name);
        writeBytes(out, host);
        writeBytes(out, interval_category);
        writeBytes(out, interval_clique);
        out.writeLong(interval_millis);
        writeBytes(out, request_body);
        writeBytes(out, request_content_type);
        writeBytes(out, request_headers);
        writeBytes(out, request_method);
        writeBytes(out, request_params);
        writeBytes(out, request_url);
        writeBytes(out, request_user_agent);
        writeBytes(out, response_body);
        writeBytes(out, response_code);
        writeBytes(out, response_content_type);
        writeBytes(out, response_headers);
        out.writeLong(response_time_millis);
        writeBytes(out, size_category);
        out.writeInt(size_request);
        out.writeInt(size_response);
    }

    /**
     * Reads byte array from input stream.
     */
    private byte[] readBytes(ObjectInput in) throws IOException {
        int len = in.readInt();
        if (len == 0) {
            return null;
        } else {
            byte[] bytes = new byte[len];
            in.readFully(bytes);
            return bytes;
        }
    }

    /**
     * Writes byte array to output stream.
     */
    private void writeBytes(ObjectOutput out, byte[] b) throws IOException {
        if (b == null) {
            out.writeInt(0);
        } else {
            out.writeInt(b.length);
            out.write(b);
        }
    }

}
