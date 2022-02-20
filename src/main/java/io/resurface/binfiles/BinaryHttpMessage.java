// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import it.unimi.dsi.fastutil.io.FastBufferedInputStream;
import it.unimi.dsi.fastutil.io.FastBufferedOutputStream;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Binary format for HTTP messages.
 */
public final class BinaryHttpMessage {

    public final BinaryHttpMessageString id = new BinaryHttpMessageString();                                 // 0
    public final BinaryHttpMessageString agent_category = new BinaryHttpMessageString();                     // 1
    public final BinaryHttpMessageString agent_device = new BinaryHttpMessageString();                       // 2
    public final BinaryHttpMessageString agent_name = new BinaryHttpMessageString();                         // 3
    public final BinaryHttpMessageString graphql_operations = new BinaryHttpMessageString();                 // 4 (v3)
    public final BinaryHttpMessageInteger graphql_operations_count = new BinaryHttpMessageInteger();         // 5 (v3)
    public final BinaryHttpMessageString host = new BinaryHttpMessageString();                               // 6
    public final BinaryHttpMessageLong interval_millis = new BinaryHttpMessageLong();                        // 7
    public final BinaryHttpMessageString request_body = new BinaryHttpMessageString();                       // 8
    public final BinaryHttpMessageString request_content_type = new BinaryHttpMessageString();               // 9
    public final BinaryHttpMessageString request_headers = new BinaryHttpMessageString();                    // 10
    public final BinaryHttpMessageString request_json_type = new BinaryHttpMessageString();                  // 11
    public final BinaryHttpMessageString request_method = new BinaryHttpMessageString();                     // 12
    public final BinaryHttpMessageString request_params = new BinaryHttpMessageString();                     // 13
    public final BinaryHttpMessageString request_url = new BinaryHttpMessageString();                        // 14
    public final BinaryHttpMessageString request_user_agent = new BinaryHttpMessageString();                 // 15
    public final BinaryHttpMessageString response_body = new BinaryHttpMessageString();                      // 16
    public final BinaryHttpMessageString response_code = new BinaryHttpMessageString();                      // 17
    public final BinaryHttpMessageString response_content_type = new BinaryHttpMessageString();              // 18
    public final BinaryHttpMessageString response_headers = new BinaryHttpMessageString();                   // 19
    public final BinaryHttpMessageString response_json_type = new BinaryHttpMessageString();                 // 20
    public final BinaryHttpMessageLong response_time_millis = new BinaryHttpMessageLong();                   // 21
    public final BinaryHttpMessageInteger size_request_bytes = new BinaryHttpMessageInteger();               // 22
    public final BinaryHttpMessageInteger size_response_bytes = new BinaryHttpMessageInteger();              // 23
    public final BinaryHttpMessageString custom_fields = new BinaryHttpMessageString();                      // 24 (v3)
    public final BinaryHttpMessageString request_address = new BinaryHttpMessageString();                    // 25 (v3)
    public final BinaryHttpMessageString session_fields = new BinaryHttpMessageString();                     // 26 (v3)
    public final BinaryHttpMessageString cookies = new BinaryHttpMessageString();                            // 27 (v3)
    public final BinaryHttpMessageInteger cookies_count = new BinaryHttpMessageInteger();                    // 28 (v3)

    private byte[] buffer;
    private ByteBuffer bytebuffer;
    private final byte[] header = new byte[8];

    /**
     * Default constructor.
     */
    public BinaryHttpMessage() {
        allocate(8);
    }

    /**
     * Allocates internal buffer with specified length.
     */
    public void allocate(int length) {
        this.buffer = new byte[length];  // todo need guard against huge buffer sizes!
        this.bytebuffer = ByteBuffer.wrap(buffer);

        id.buffer(buffer);                                                                                   // 0
        agent_category.buffer(buffer);                                                                       // 1
        agent_device.buffer(buffer);                                                                         // 2
        agent_name.buffer(buffer);                                                                           // 3
        graphql_operations.buffer(buffer);                                                                   // 4 (v3)
        // skip graphql_operations_count                                                                     // 5 (v3)
        host.buffer(buffer);                                                                                 // 6
        // skip interval_millis                                                                              // 7
        request_body.buffer(buffer);                                                                         // 8
        request_content_type.buffer(buffer);                                                                 // 9
        request_headers.buffer(buffer);                                                                      // 10
        request_json_type.buffer(buffer);                                                                    // 11
        request_method.buffer(buffer);                                                                       // 12
        request_params.buffer(buffer);                                                                       // 13
        request_url.buffer(buffer);                                                                          // 14
        request_user_agent.buffer(buffer);                                                                   // 15
        response_body.buffer(buffer);                                                                        // 16
        response_code.buffer(buffer);                                                                        // 17
        response_content_type.buffer(buffer);                                                                // 18
        response_headers.buffer(buffer);                                                                     // 19
        response_json_type.buffer(buffer);                                                                   // 20
        // skip response_time_millis                                                                         // 21
        // skip size_request_bytes                                                                           // 22
        // skip size_response_bytes                                                                          // 23
        custom_fields.buffer(buffer);                                                                        // 24 (v3)
        request_address.buffer(buffer);                                                                      // 25 (v3)
        session_fields.buffer(buffer);                                                                       // 26 (v3)
        cookies.buffer(buffer);                                                                              // 27 (v3)
        // skip cookies_count                                                                                // 28 (v3)
    }

    /**
     * Returns the length of this message in bytes.
     */
    public int length() {
        int result = 8;
        result += id.bytes();                                                                                // 0
        result += agent_category.bytes();                                                                    // 1
        result += agent_device.bytes();                                                                      // 2
        result += agent_name.bytes();                                                                        // 3
        result += graphql_operations.bytes();                                                                // 4 (v3)
        result += graphql_operations_count.bytes();                                                          // 5 (v3)
        result += host.bytes();                                                                              // 6
        result += interval_millis.bytes();                                                                   // 7
        result += request_body.bytes();                                                                      // 8
        result += request_content_type.bytes();                                                              // 9
        result += request_headers.bytes();                                                                   // 10
        result += request_json_type.bytes();                                                                 // 11
        result += request_method.bytes();                                                                    // 12
        result += request_params.bytes();                                                                    // 13
        result += request_url.bytes();                                                                       // 14
        result += request_user_agent.bytes();                                                                // 15
        result += response_body.bytes();                                                                     // 16
        result += response_code.bytes();                                                                     // 17
        result += response_content_type.bytes();                                                             // 18
        result += response_headers.bytes();                                                                  // 19
        result += response_json_type.bytes();                                                                // 20
        result += response_time_millis.bytes();                                                              // 21
        result += size_request_bytes.bytes();                                                                // 22
        result += size_response_bytes.bytes();                                                               // 23
        result += custom_fields.bytes();                                                                     // 24 (v3)
        result += request_address.bytes();                                                                   // 25 (v3)
        result += session_fields.bytes();                                                                    // 26 (v3)
        result += cookies.bytes();                                                                           // 27 (v3)
        result += cookies_count.bytes();                                                                     // 28 (v3)
        return result;
    }

    /**
     * Reads all message fields from input stream.
     */
    public void read(FastBufferedInputStream in) throws IOException {
        int len = readHeader(in);

        if (buffer.length < len) allocate(len);
        if (in.read(buffer, 0, len) < len) throw new EOFException();
        ByteBuffer bb = bytebuffer.rewind();

        int offset = 124;
        offset += id.read(offset, bb.getInt());                                                          // 0
        offset += agent_category.read(offset, bb.getInt());                                              // 1
        offset += agent_device.read(offset, bb.getInt());                                                // 2
        offset += agent_name.read(offset, bb.getInt());                                                  // 3
        offset += graphql_operations.read(offset, bb.getInt());                                          // 4 (v3)
        graphql_operations_count.read(bb.getInt());                                                      // 5 (v3)
        offset += host.read(offset, bb.getInt());                                                        // 6
        interval_millis.read(bb.getLong());                                                              // 7
        offset += request_body.read(offset, bb.getInt());                                                // 8
        offset += request_content_type.read(offset, bb.getInt());                                        // 9
        offset += request_headers.read(offset, bb.getInt());                                             // 10
        offset += request_json_type.read(offset, bb.getInt());                                           // 11
        offset += request_method.read(offset, bb.getInt());                                              // 12
        offset += request_params.read(offset, bb.getInt());                                              // 13
        offset += request_url.read(offset, bb.getInt());                                                 // 14
        offset += request_user_agent.read(offset, bb.getInt());                                          // 15
        offset += response_body.read(offset, bb.getInt());                                               // 16
        offset += response_code.read(offset, bb.getInt());                                               // 17
        offset += response_content_type.read(offset, bb.getInt());                                       // 18
        offset += response_headers.read(offset, bb.getInt());                                            // 19
        offset += response_json_type.read(offset, bb.getInt());                                          // 20
        response_time_millis.read(bb.getLong());                                                         // 21
        size_request_bytes.read(bb.getInt());                                                            // 22
        size_response_bytes.read(bb.getInt());                                                           // 23
        offset += custom_fields.read(offset, bb.getInt());                                               // 24 (v3)
        offset += request_address.read(offset, bb.getInt());                                             // 25 (v3)
        offset += session_fields.read(offset, bb.getInt());                                              // 26 (v3)
        cookies.read(offset, bb.getInt());                                                               // 27 (v3)
        cookies_count.read(bb.getInt());                                                                 // 28 (v3)
    }

    /**
     * Writes all message fields to output stream.
     */
    public void write(FastBufferedOutputStream out, byte[] buf) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(buf);

        // write fixed-length data
        id.write(bb);                                                                                        // 0
        agent_category.write(bb);                                                                            // 1
        agent_device.write(bb);                                                                              // 2
        agent_name.write(bb);                                                                                // 3
        graphql_operations.write(bb);                                                                        // 4 (v3)
        graphql_operations_count.write(bb);                                                                  // 5 (v3)
        host.write(bb);                                                                                      // 6
        interval_millis.write(bb);                                                                           // 7
        request_body.write(bb);                                                                              // 8
        request_content_type.write(bb);                                                                      // 9
        request_headers.write(bb);                                                                           // 10
        request_json_type.write(bb);                                                                         // 11
        request_method.write(bb);                                                                            // 12
        request_params.write(bb);                                                                            // 13
        request_url.write(bb);                                                                               // 14
        request_user_agent.write(bb);                                                                        // 15
        response_body.write(bb);                                                                             // 16
        response_code.write(bb);                                                                             // 17
        response_content_type.write(bb);                                                                     // 18
        response_headers.write(bb);                                                                          // 19
        response_json_type.write(bb);                                                                        // 20
        response_time_millis.write(bb);                                                                      // 21
        size_request_bytes.write(bb);                                                                        // 22
        size_response_bytes.write(bb);                                                                       // 23
        custom_fields.write(bb);                                                                             // 24 (v3)
        request_address.write(bb);                                                                           // 25 (v3)
        session_fields.write(bb);                                                                            // 26 (v3)
        cookies.write(bb);                                                                                   // 27 (v3)
        cookies_count.write(bb);                                                                             // 28 (v3)

        // write variable-length data
        id.write2(bb);                                                                                       // 0
        agent_category.write2(bb);                                                                           // 1
        agent_device.write2(bb);                                                                             // 2
        agent_name.write2(bb);                                                                               // 3
        graphql_operations.write2(bb);                                                                       // 4 (v3)
        // skip graphql_operations_count                                                                     // 5 (v3)
        host.write2(bb);                                                                                     // 6
        // skip interval_millis                                                                              // 7
        request_body.write2(bb);                                                                             // 8
        request_content_type.write2(bb);                                                                     // 9
        request_headers.write2(bb);                                                                          // 10
        request_json_type.write2(bb);                                                                        // 11
        request_method.write2(bb);                                                                           // 12
        request_params.write2(bb);                                                                           // 13
        request_url.write2(bb);                                                                              // 14
        request_user_agent.write2(bb);                                                                       // 15
        response_body.write2(bb);                                                                            // 16
        response_code.write2(bb);                                                                            // 17
        response_content_type.write2(bb);                                                                    // 18
        response_headers.write2(bb);                                                                         // 19
        response_json_type.write2(bb);                                                                       // 20
        // skip response_time_millis                                                                         // 21
        // skip size_request_bytes                                                                           // 22
        // skip size_response_bytes                                                                          // 23
        custom_fields.write2(bb);                                                                            // 24 (v3)
        request_address.write2(bb);                                                                          // 25 (v3)
        session_fields.write2(bb);                                                                           // 26 (v3)
        cookies.write2(bb);                                                                                  // 27 (v3)
        // skip cookies_count                                                                                // 28 (v3)

        // write to stream
        writeInt(out, 30);
        int position = bb.position();
        writeInt(out, position);
        out.write(buf, 0, position);
    }

    /**
     * Reads message header, verifies magic values and returns length in bytes for the remainder of the message.
     */
    private int readHeader(FastBufferedInputStream in) throws IOException {
        if (in.read(header, 0, 8) < 8) throw new EOFException();
        if ((header[0] != 0) || (header[1] != 0) || (header[2] != 0))
            throw new RuntimeException("Invalid header padding");
        if (header[3] != 30) throw new RuntimeException("Invalid header version");
        return fromBytes(header[4], header[5], header[6], header[7]);
    }

    /**
     * Returns integer from 4 bytes in big-endian order.
     */
    private static int fromBytes(byte b1, byte b2, byte b3, byte b4) {
        return b1 << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
    }

    /**
     * Writes integer to output stream.
     */
    private void writeInt(FastBufferedOutputStream out, int value) throws IOException {
        if (value == 0) {
            out.write(0);
            out.write(0);
            out.write(0);
            out.write(0);
        } else {
            out.write((value >>> 24) & 0xFF);
            out.write((value >>> 16) & 0xFF);
            out.write((value >>> 8) & 0xFF);
            out.write((value) & 0xFF);
        }
    }

}
