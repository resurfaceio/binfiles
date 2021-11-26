// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Binary format for HTTP messages.
 */
public final class BinaryHttpMessage {

    public final BinaryHttpMessageInteger delimiter = new BinaryHttpMessageInteger();
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

    /**
     * Returns the length of this message in bytes.
     */
    public int length() {
        int result = delimiter.length();
        result += id.length();                             // 0
        result += agent_category.length();                 // 1
        result += agent_device.length();                   // 2
        result += agent_name.length();                     // 3
        result += graphql_operations.length();             // 4 (v3)
        result += graphql_operations_count.length();       // 5 (v3)
        result += host.length();                           // 6
        result += interval_millis.length();                // 7
        result += request_body.length();                   // 8
        result += request_content_type.length();           // 9
        result += request_headers.length();                // 10
        result += request_json_type.length();              // 11
        result += request_method.length();                 // 12
        result += request_params.length();                 // 13
        result += request_url.length();                    // 14
        result += request_user_agent.length();             // 15
        result += response_body.length();                  // 16
        result += response_code.length();                  // 17
        result += response_content_type.length();          // 18
        result += response_headers.length();               // 19
        result += response_json_type.length();             // 20
        result += response_time_millis.length();           // 21
        result += size_request_bytes.length();             // 22
        result += size_response_bytes.length();            // 23
        result += custom_fields.length();                  // 24 (v3)
        result += request_address.length();                // 25 (v3)
        result += session_fields.length();                 // 26 (v3)
        result += cookies.length();                        // 27 (v3)
        result += cookies_count.length();                  // 28 (v3)
        return result;
    }

    /**
     * Reads all message fields from input stream.
     */
    public void read(DataInput in) throws IOException {
        delimiter.read(in);
        if (delimiter.value() == 30) {
            id.read(in);                                   // 0
            agent_category.read(in);                       // 1
            agent_device.read(in);                         // 2
            agent_name.read(in);                           // 3
            graphql_operations.read(in);                   // 4 (v3)
            graphql_operations_count.read(in);             // 5 (v3)
            host.read(in);                                 // 6
            interval_millis.read(in);                      // 7
            request_body.read(in);                         // 8
            request_content_type.read(in);                 // 9
            request_headers.read(in);                      // 10
            request_json_type.read(in);                    // 11
            request_method.read(in);                       // 12
            request_params.read(in);                       // 13
            request_url.read(in);                          // 14
            request_user_agent.read(in);                   // 15
            response_body.read(in);                        // 16
            response_code.read(in);                        // 17
            response_content_type.read(in);                // 18
            response_headers.read(in);                     // 19
            response_json_type.read(in);                   // 20
            response_time_millis.read(in);                 // 21
            size_request_bytes.read(in);                   // 22
            size_response_bytes.read(in);                  // 23
            custom_fields.read(in);                        // 24 (v3)
            request_address.read(in);                      // 25 (v3)
            session_fields.read(in);                       // 26 (v3)
            cookies.read(in);                              // 27 (v3)
            cookies_count.read(in);                        // 28 (v3)
        } else throw new RuntimeException("Invalid record delimiter");
    }

    /**
     * Writes all message fields to output stream.
     */
    public void write(DataOutput out) throws IOException {
        delimiter.read(30);
        delimiter.write(out);
        id.write(out);                                     // 0
        agent_category.write(out);                         // 1
        agent_device.write(out);                           // 2
        agent_name.write(out);                             // 3
        graphql_operations.write(out);                     // 4 (v3)
        graphql_operations_count.write(out);               // 5 (v3)
        host.write(out);                                   // 6
        interval_millis.write(out);                        // 7
        request_body.write(out);                           // 8
        request_content_type.write(out);                   // 9
        request_headers.write(out);                        // 10
        request_json_type.write(out);                      // 11
        request_method.write(out);                         // 12
        request_params.write(out);                         // 13
        request_url.write(out);                            // 14
        request_user_agent.write(out);                     // 15
        response_body.write(out);                          // 16
        response_code.write(out);                          // 17
        response_content_type.write(out);                  // 18
        response_headers.write(out);                       // 19
        response_json_type.write(out);                     // 20
        response_time_millis.write(out);                   // 21
        size_request_bytes.write(out);                     // 22
        size_response_bytes.write(out);                    // 23
        custom_fields.write(out);                          // 24 (v3)
        request_address.write(out);                        // 25 (v3)
        session_fields.write(out);                         // 26 (v3)
        cookies.write(out);                                // 27 (v3)
        cookies_count.write(out);                          // 28 (v3)
    }

}
