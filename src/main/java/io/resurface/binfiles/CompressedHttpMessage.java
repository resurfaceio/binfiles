// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Persistent binary format for HTTP messages with automatic field-level compression.
 */
public final class CompressedHttpMessage extends PersistentHttpMessage {

    /**
     * Default constructor.
     */
    public CompressedHttpMessage() {
        this.id = new BinaryHttpMessageString();                                 // 0
        this.agent_category = new BinaryHttpMessageString();                     // 1
        this.agent_device = new BinaryHttpMessageString();                       // 2
        this.agent_name = new BinaryHttpMessageString();                         // 3
        this.graphql_operations = new BinaryHttpMessageString();                 // 4 (v3)
        this.graphql_operations_count = new BinaryHttpMessageInteger();         // 5 (v3)
        this.host = new BinaryHttpMessageString();                               // 6
        this.interval_millis = new BinaryHttpMessageLong();                        // 7
        this.request_body = new BinaryHttpMessageString();                       // 8
        this.request_content_type = new BinaryHttpMessageString();               // 9
        this.request_headers = new CompressedHttpMessageString();            // 10
        this.request_json_type = new BinaryHttpMessageString();                  // 11
        this.request_method = new BinaryHttpMessageString();                     // 12
        this.request_params = new BinaryHttpMessageString();                     // 13
        this.request_url = new BinaryHttpMessageString();                        // 14
        this.request_user_agent = new BinaryHttpMessageString();                 // 15
        this.response_body = new CompressedHttpMessageString();              // 16
        this.response_code = new BinaryHttpMessageString();                      // 17
        this.response_content_type = new BinaryHttpMessageString();              // 18
        this.response_headers = new BinaryHttpMessageString();                   // 19
        this.response_json_type = new BinaryHttpMessageString();                 // 20
        this.response_time_millis = new BinaryHttpMessageLong();                   // 21
        this.size_request_bytes = new BinaryHttpMessageInteger();               // 22
        this.size_response_bytes = new BinaryHttpMessageInteger();              // 23
        this.custom_fields = new BinaryHttpMessageString();                      // 24 (v3)
        this.request_address = new BinaryHttpMessageString();                    // 25 (v3)
        this.session_fields = new BinaryHttpMessageString();                     // 26 (v3)
        this.cookies = new BinaryHttpMessageString();                            // 27 (v3)
        this.cookies_count = new BinaryHttpMessageInteger();                    // 28 (v3)
        // reserved for response_status                                                                          // 29 (v3.1)
        // reserved for size_total_bytes                                                                         // 30 (v3.1)
        this.bitmap_versioning = new BinaryHttpMessageInteger();                // 31 (v3.1)
        this.bitmap_request_info = new BinaryHttpMessageInteger();              // 32 (v3.1)
        this.bitmap_request_json = new BinaryHttpMessageInteger();              // 33 (v3.1)
        this.bitmap_request_graphql = new BinaryHttpMessageInteger();           // 34 (v3.1)
        this.bitmap_request_pii = new BinaryHttpMessageInteger();               // 35 (v3.1)
        this.bitmap_request_threat = new BinaryHttpMessageInteger();            // 36 (v3.1)
        this.bitmap_response_info = new BinaryHttpMessageInteger();             // 37 (v3.1)
        this.bitmap_response_json = new BinaryHttpMessageInteger();             // 38 (v3.1)
        this.bitmap_response_pii = new BinaryHttpMessageInteger();              // 39 (v3.1)
        this.bitmap_response_threat = new BinaryHttpMessageInteger();           // 40 (v3.1)
        this.bitmap_attack_request = new BinaryHttpMessageInteger();            // 41 (v3.1)
        this.bitmap_attack_application = new BinaryHttpMessageInteger();        // 42 (v3.1)
        this.bitmap_attack_injection = new BinaryHttpMessageInteger();          // 43 (v3.1)
        this.bitmap_response_leak = new BinaryHttpMessageInteger();             // 44 (v3.1)
        this.bitmap_unused2 = new BinaryHttpMessageInteger();                   // 45 (v3.1)
        this.bitmap_unused3 = new BinaryHttpMessageInteger();                   // 46 (v3.1)
        this.bitmap_unused4 = new BinaryHttpMessageInteger();                   // 47 (v3.1)
        this.bitmap_unused5 = new BinaryHttpMessageInteger();                   // 48 (v3.1)
    }

    private byte[] buffer;
    private ByteBuffer bytebuffer;
    private final byte[] header = new byte[8];

    /**
     * Allocates internal buffer with specified length.
     */
    private void allocate(int length) {
        this.buffer = new byte[length];
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
        // skip response_status                                                                              // 29 (v3.1)
        // skip size_total_bytes                                                                             // 30 (v3.1)
        // skip bitmap_versioning                                                                            // 31 (v3.1)
        // skip bitmap_request_info                                                                          // 32 (v3.1)
        // skip bitmap_request_json                                                                          // 33 (v3.1)
        // skip bitmap_request_graphql                                                                       // 34 (v3.1)
        // skip bitmap_request_pii                                                                           // 35 (v3.1)
        // skip bitmap_request_threat                                                                        // 36 (v3.1)
        // skip bitmap_response_info                                                                         // 37 (v3.1)
        // skip bitmap_response_json                                                                         // 38 (v3.1)
        // skip bitmap_response_pii                                                                          // 39 (v3.1)
        // skip bitmap_response_threat                                                                       // 40 (v3.1)
        // skip bitmap_attack_request                                                                        // 41 (v3.1)
        // skip bitmap_attack_application                                                                    // 42 (v3.1)
        // skip bitmap_attack_injection                                                                      // 43 (v3.1)
        // skip bitmap_response_leak                                                                         // 44 (v3.1)
        // skip bitmap_unused2                                                                               // 45 (v3.1)
        // skip bitmap_unused3                                                                               // 46 (v3.1)
        // skip bitmap_unused4                                                                               // 47 (v3.1)
        // skip bitmap_unused5                                                                               // 48 (v3.1)
    }

    /**
     * Returns message size in bytes, including all metadata.
     */
    public int bytes() {
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
        // skip response_status                                                                              // 29 (v3.1)
        // skip size_total_bytes                                                                             // 30 (v3.1)
        result += bitmap_versioning.bytes();                                                                 // 31 (v3.1)
        result += bitmap_request_info.bytes();                                                               // 32 (v3.1)
        result += bitmap_request_json.bytes();                                                               // 33 (v3.1)
        result += bitmap_request_graphql.bytes();                                                            // 34 (v3.1)
        result += bitmap_request_pii.bytes();                                                                // 35 (v3.1)
        result += bitmap_request_threat.bytes();                                                             // 36 (v3.1)
        result += bitmap_response_info.bytes();                                                              // 37 (v3.1)
        result += bitmap_response_json.bytes();                                                              // 38 (v3.1)
        result += bitmap_response_pii.bytes();                                                               // 39 (v3.1)
        result += bitmap_response_threat.bytes();                                                            // 40 (v3.1)
        result += bitmap_attack_request.bytes();                                                             // 41 (v3.1)
        result += bitmap_attack_application.bytes();                                                         // 42 (v3.1)
        result += bitmap_attack_injection.bytes();                                                           // 43 (v3.1)
        result += bitmap_response_leak.bytes();                                                              // 44 (v3.1)
        result += bitmap_unused2.bytes();                                                                    // 45 (v3.1)
        result += bitmap_unused3.bytes();                                                                    // 46 (v3.1)
        result += bitmap_unused4.bytes();                                                                    // 47 (v3.1)
        result += bitmap_unused5.bytes();                                                                    // 48 (v3.1)
        return result;
    }

    /**
     * Reads all message fields from input stream.
     */
    public void read(InputStream in) throws IOException {
        if (in.read(header, 0, 8) < 8) throw new EOFException();
        if ((header[0] != 0) || (header[1] != 0) || (header[2] != 0))
            throw new RuntimeException("Invalid header padding");

        if (header[3] != 33) throw new RuntimeException("Invalid header version");

        int len = readInt(header[4], header[5], header[6], header[7]);
        if (buffer == null || buffer.length < len) allocate(len);
        if (in.read(buffer, 0, len) < len) throw new EOFException();
        ByteBuffer bb = bytebuffer.rewind();

        int offset = 204;
        offset += id.read(offset, bb);                                                              // 0
        offset += agent_category.read(offset, bb);                                                  // 1
        offset += agent_device.read(offset, bb);                                                    // 2
        offset += agent_name.read(offset, bb);                                                      // 3
        offset += graphql_operations.read(offset, bb);                                              // 4 (v3)
        graphql_operations_count.read(bb.getInt());                                                          // 5 (v3)
        offset += host.read(offset, bb);                                                            // 6
        interval_millis.read(bb.getLong());                                                                  // 7
        offset += request_body.read(offset, bb);                                                    // 8
        offset += request_content_type.read(offset, bb);                                            // 9
        offset += request_headers.read(offset, bb);                                    // 10
        offset += request_json_type.read(offset, bb);                                               // 11
        offset += request_method.read(offset, bb);                                                  // 12
        offset += request_params.read(offset, bb);                                                  // 13
        offset += request_url.read(offset, bb);                                                     // 14
        offset += request_user_agent.read(offset, bb);                                              // 15
        offset += response_body.read(offset, bb);                                      // 16
        offset += response_code.read(offset, bb);                                                   // 17
        offset += response_content_type.read(offset, bb);                                           // 18
        offset += response_headers.read(offset, bb);                                                // 19
        offset += response_json_type.read(offset, bb);                                              // 20
        response_time_millis.read(bb.getLong());                                                             // 21
        size_request_bytes.read(bb.getInt());                                                                // 22
        size_response_bytes.read(bb.getInt());                                                               // 23
        offset += custom_fields.read(offset, bb);                                                   // 24 (v3)
        offset += request_address.read(offset, bb);                                                 // 25 (v3)
        offset += session_fields.read(offset, bb);                                                  // 26 (v3)
        cookies.read(offset, bb);                                                                   // 27 (v3)
        cookies_count.read(bb.getInt());                                                                     // 28 (v3)
        // skip response_status                                                                              // 29 (v3.1)
        // skip size_total_bytes                                                                             // 30 (v3.1)
        bitmap_versioning.read(bb.getInt());                                                                 // 31 (v3.1)
        bitmap_request_info.read(bb.getInt());                                                               // 32 (v3.1)
        bitmap_request_json.read(bb.getInt());                                                               // 33 (v3.1)
        bitmap_request_graphql.read(bb.getInt());                                                            // 34 (v3.1)
        bitmap_request_pii.read(bb.getInt());                                                                // 35 (v3.1)
        bitmap_request_threat.read(bb.getInt());                                                             // 36 (v3.1)
        bitmap_response_info.read(bb.getInt());                                                              // 37 (v3.1)
        bitmap_response_json.read(bb.getInt());                                                              // 38 (v3.1)
        bitmap_response_pii.read(bb.getInt());                                                               // 39 (v3.1)
        bitmap_response_threat.read(bb.getInt());                                                            // 40 (v3.1)
        bitmap_attack_request.read(bb.getInt());                                                             // 41 (v3.1)
        bitmap_attack_application.read(bb.getInt());                                                         // 42 (v3.1)
        bitmap_attack_injection.read(bb.getInt());                                                           // 43 (v3.1)
        bitmap_response_leak.read(bb.getInt());                                                              // 44 (v3.1)
        bitmap_unused2.read(bb.getInt());                                                                    // 45 (v3.1)
        bitmap_unused3.read(bb.getInt());                                                                    // 46 (v3.1)
        bitmap_unused4.read(bb.getInt());                                                                    // 47 (v3.1)
        bitmap_unused5.read(bb.getInt());                                                                    // 48 (v3.1)
    }

    /**
     * Writes all message fields to output stream, using reusable temporary buffer.
     */
    public void write(OutputStream out, byte[] temp) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(temp);

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
        // skip response_status                                                                              // 29 (v3.1)
        // skip size_total_bytes                                                                             // 30 (v3.1)
        bitmap_versioning.write(bb);                                                                         // 31 (v3.1)
        bitmap_request_info.write(bb);                                                                       // 32 (v3.1)
        bitmap_request_json.write(bb);                                                                       // 33 (v3.1)
        bitmap_request_graphql.write(bb);                                                                    // 34 (v3.1)
        bitmap_request_pii.write(bb);                                                                        // 35 (v3.1)
        bitmap_request_threat.write(bb);                                                                     // 36 (v3.1)
        bitmap_response_info.write(bb);                                                                      // 37 (v3.1)
        bitmap_response_json.write(bb);                                                                      // 38 (v3.1)
        bitmap_response_pii.write(bb);                                                                       // 39 (v3.1)
        bitmap_response_threat.write(bb);                                                                    // 40 (v3.1)
        bitmap_attack_request.write(bb);                                                                     // 41 (v3.1)
        bitmap_attack_application.write(bb);                                                                 // 42 (v3.1)
        bitmap_attack_injection.write(bb);                                                                   // 43 (v3.1)
        bitmap_response_leak.write(bb);                                                                      // 44 (v3.1)
        bitmap_unused2.write(bb);                                                                            // 45 (v3.1)
        bitmap_unused3.write(bb);                                                                            // 46 (v3.1)
        bitmap_unused4.write(bb);                                                                            // 47 (v3.1)
        bitmap_unused5.write(bb);                                                                            // 48 (v3.1)

        // write variable-length data
        id.writeContents(bb);                                                                                       // 0
        agent_category.writeContents(bb);                                                                           // 1
        agent_device.writeContents(bb);                                                                             // 2
        agent_name.writeContents(bb);                                                                               // 3
        graphql_operations.writeContents(bb);                                                                       // 4 (v3)
        // skip graphql_operations_count                                                                     // 5 (v3)
        host.writeContents(bb);                                                                                     // 6
        // skip interval_millis                                                                              // 7
        request_body.writeContents(bb);                                                                             // 8
        request_content_type.writeContents(bb);                                                                     // 9
        request_headers.writeContents(bb);                                                                          // 10
        request_json_type.writeContents(bb);                                                                        // 11
        request_method.writeContents(bb);                                                                           // 12
        request_params.writeContents(bb);                                                                           // 13
        request_url.writeContents(bb);                                                                              // 14
        request_user_agent.writeContents(bb);                                                                       // 15
        response_body.writeContents(bb);                                                                            // 16
        response_code.writeContents(bb);                                                                            // 17
        response_content_type.writeContents(bb);                                                                    // 18
        response_headers.writeContents(bb);                                                                         // 19
        response_json_type.writeContents(bb);                                                                       // 20
        // skip response_time_millis                                                                         // 21
        // skip size_request_bytes                                                                           // 22
        // skip size_response_bytes                                                                          // 23
        custom_fields.writeContents(bb);                                                                            // 24 (v3)
        request_address.writeContents(bb);                                                                          // 25 (v3)
        session_fields.writeContents(bb);                                                                           // 26 (v3)
        cookies.writeContents(bb);                                                                                  // 27 (v3)
        // skip cookies_count                                                                                // 28 (v3)
        // skip response_status                                                                              // 29 (v3.1)
        // skip size_total_bytes                                                                             // 30 (v3.1)
        // skip bitmap_versioning                                                                            // 31 (v3.1)
        // skip bitmap_request_info                                                                          // 32 (v3.1)
        // skip bitmap_request_json                                                                          // 33 (v3.1)
        // skip bitmap_request_graphql                                                                       // 34 (v3.1)
        // skip bitmap_request_pii                                                                           // 35 (v3.1)
        // skip bitmap_request_threat                                                                        // 36 (v3.1)
        // skip bitmap_response_info                                                                         // 37 (v3.1)
        // skip bitmap_response_json                                                                         // 38 (v3.1)
        // skip bitmap_response_pii                                                                          // 39 (v3.1)
        // skip bitmap_response_threat                                                                       // 40 (v3.1)
        // skip bitmap_attack_request                                                                        // 41 (v3.1)
        // skip bitmap_attack_application                                                                    // 42 (v3.1)
        // skip bitmap_attack_injection                                                                      // 43 (v3.1)
        // skip bitmap_response_leak                                                                         // 44 (v3.1)
        // skip bitmap_unused2                                                                               // 45 (v3.1)
        // skip bitmap_unused3                                                                               // 46 (v3.1)
        // skip bitmap_unused4                                                                               // 47 (v3.1)
        // skip bitmap_unused5                                                                               // 48 (v3.1)

        // write to stream
        writeInt(out, 33);
        int position = bb.position();
        writeInt(out, position);
        out.write(temp, 0, position);
    }

}
