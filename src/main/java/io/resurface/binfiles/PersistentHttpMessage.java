// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Abstract structure for persistent HTTP messages.
 */
public abstract class PersistentHttpMessage {

    public PersistentHttpMessageString id;                                                                                         // 0
    public PersistentHttpMessageString agent_category;                                                                             // 1
    public PersistentHttpMessageString agent_device;                                                                               // 2
    public PersistentHttpMessageString agent_name;                                                                                 // 3
    public PersistentHttpMessageString graphql_operations;                                                                         // 4 (v3)
    public BinaryHttpMessageInteger graphql_operations_count;                                                                      // 5 (v3)
    public PersistentHttpMessageString host;                                                                                       // 6
    public BinaryHttpMessageLong interval_millis;                                                                                  // 7
    public PersistentHttpMessageString request_body;                                                                               // 8
    public PersistentHttpMessageString request_content_type;                                                                       // 9
    public PersistentHttpMessageString request_headers;                                                                            // 10
    public PersistentHttpMessageString request_json_type;                                                                          // 11
    public PersistentHttpMessageString request_method;                                                                             // 12
    public PersistentHttpMessageString request_params;                                                                             // 13
    public PersistentHttpMessageString request_url;                                                                                // 14
    public PersistentHttpMessageString request_user_agent;                                                                         // 15
    public PersistentHttpMessageString response_body;                                                                              // 16
    public PersistentHttpMessageString response_code;                                                                              // 17
    public PersistentHttpMessageString response_content_type;                                                                      // 18
    public PersistentHttpMessageString response_headers;                                                                           // 19
    public PersistentHttpMessageString response_json_type;                                                                         // 20
    public BinaryHttpMessageLong response_time_millis;                                                                             // 21
    public BinaryHttpMessageInteger size_request_bytes;                                                                            // 22
    public BinaryHttpMessageInteger size_response_bytes;                                                                           // 23
    public PersistentHttpMessageString custom_fields;                                                                              // 24 (v3)
    public PersistentHttpMessageString request_address;                                                                            // 25 (v3)
    public PersistentHttpMessageString session_fields;                                                                             // 26 (v3)
    public PersistentHttpMessageString cookies;                                                                                    // 27 (v3)
    public BinaryHttpMessageInteger cookies_count;                                                                                 // 28 (v3)
    // reserved for response_status                                                                                                // 29 (v3.1)
    // reserved for size_total_bytes                                                                                               // 30 (v3.1)
    public BinaryHttpMessageInteger bitmap_versioning;                                                                             // 31 (v3.1)
    public BinaryHttpMessageInteger bitmap_request_info;                                                                           // 32 (v3.1)
    public BinaryHttpMessageInteger bitmap_request_json;                                                                           // 33 (v3.1)
    public BinaryHttpMessageInteger bitmap_request_graphql;                                                                        // 34 (v3.1)
    public BinaryHttpMessageInteger bitmap_request_pii;                                                                            // 35 (v3.1)
    public BinaryHttpMessageInteger bitmap_request_threat;                                                                         // 36 (v3.1)
    public BinaryHttpMessageInteger bitmap_response_info;                                                                          // 37 (v3.1)
    public BinaryHttpMessageInteger bitmap_response_json;                                                                          // 38 (v3.1)
    public BinaryHttpMessageInteger bitmap_response_pii;                                                                           // 39 (v3.1)
    public BinaryHttpMessageInteger bitmap_response_threat;                                                                        // 40 (v3.1)
    public BinaryHttpMessageInteger bitmap_attack_request;                                                                         // 41 (v3.1)
    public BinaryHttpMessageInteger bitmap_attack_application;                                                                     // 42 (v3.1)
    public BinaryHttpMessageInteger bitmap_attack_injection;                                                                       // 43 (v3.1)
    public BinaryHttpMessageInteger bitmap_response_leak;                                                                          // 44 (v3.1)
    public BinaryHttpMessageInteger bitmap_unused2;                                                                                // 45 (v3.1)
    public BinaryHttpMessageInteger bitmap_unused3;                                                                                // 46 (v3.1)
    public BinaryHttpMessageInteger bitmap_unused4;                                                                                // 47 (v3.1)
    public BinaryHttpMessageInteger bitmap_unused5;                                                                                // 48 (v3.1)
    // reserved for shard_file                                                                                                     // 49 (v3.5)

    /**
     * Returns message size in bytes, including all metadata.
     */
    abstract public int bytes();

    /**
     * Reads all message fields from input stream.
     */
    abstract public void read(InputStream in) throws IOException;

    /**
     * Returns integer from 4 bytes in big-endian order.
     */
    static int readInt(byte b1, byte b2, byte b3, byte b4) {
        return b1 << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
    }

    /**
     * Writes all message fields to output stream, using reusable temporary buffer.
     */
    abstract public void write(OutputStream out, byte[] temp) throws IOException;

    /**
     * Writes integer to output stream.
     */
    static void writeInt(OutputStream out, int value) throws IOException {
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
