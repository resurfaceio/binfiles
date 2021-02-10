// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Binary format for HTTP messages.
 */
public final class BinaryHttpMessage {

    public final BinaryHttpMessageInteger delimiter = new BinaryHttpMessageInteger();
    public final BinaryHttpMessageString id = new BinaryHttpMessageString();                     // 0
    public final BinaryHttpMessageString agent_category = new BinaryHttpMessageString();         // 1
    public final BinaryHttpMessageString agent_device = new BinaryHttpMessageString();           // 2
    public final BinaryHttpMessageString agent_name = new BinaryHttpMessageString();             // 3
    public final BinaryHttpMessageString host = new BinaryHttpMessageString();                   // 4
    public final BinaryHttpMessageString interval_category = new BinaryHttpMessageString();      // 5
    public final BinaryHttpMessageString interval_clique = new BinaryHttpMessageString();        // 6
    public final BinaryHttpMessageLong interval_millis = new BinaryHttpMessageLong();            // 7
    public final BinaryHttpMessageString request_body = new BinaryHttpMessageString();           // 8
    public final BinaryHttpMessageString request_content_type = new BinaryHttpMessageString();   // 9
    public final BinaryHttpMessageString request_headers = new BinaryHttpMessageString();        // 10
    public final BinaryHttpMessageString request_method = new BinaryHttpMessageString();         // 11
    public final BinaryHttpMessageString request_params = new BinaryHttpMessageString();         // 12
    public final BinaryHttpMessageString request_url = new BinaryHttpMessageString();            // 13
    public final BinaryHttpMessageString request_user_agent = new BinaryHttpMessageString();     // 14
    public final BinaryHttpMessageString response_body = new BinaryHttpMessageString();          // 15
    public final BinaryHttpMessageString response_code = new BinaryHttpMessageString();          // 16
    public final BinaryHttpMessageString response_content_type = new BinaryHttpMessageString();  // 17
    public final BinaryHttpMessageString response_headers = new BinaryHttpMessageString();       // 18
    public final BinaryHttpMessageLong response_time_millis = new BinaryHttpMessageLong();       // 19
    public final BinaryHttpMessageString size_category = new BinaryHttpMessageString();          // 20
    public final BinaryHttpMessageInteger size_request_bytes = new BinaryHttpMessageInteger();   // 21
    public final BinaryHttpMessageInteger size_response_bytes = new BinaryHttpMessageInteger();  // 22

    /**
     * Returns the length of this message in bytes.
     */
    public int length() {
        int result = delimiter.length();
        result += id.length();                     // 0
        result += agent_category.length();         // 1
        result += agent_device.length();           // 2
        result += agent_name.length();             // 3
        result += host.length();                   // 4
        result += interval_category.length();      // 5
        result += interval_clique.length();        // 6
        result += interval_millis.length();        // 7
        result += request_body.length();           // 8
        result += request_content_type.length();   // 9
        result += request_headers.length();        // 10
        result += request_method.length();         // 11
        result += request_params.length();         // 12
        result += request_url.length();            // 13
        result += request_user_agent.length();     // 14
        result += response_body.length();          // 15
        result += response_code.length();          // 16
        result += response_content_type.length();  // 17
        result += response_headers.length();       // 18
        result += response_time_millis.length();   // 19
        result += size_category.length();          // 20
        result += size_request_bytes.length();     // 21
        result += size_response_bytes.length();    // 22
        return result;
    }

    /**
     * Reads all message fields from input stream.
     */
    public void read(ObjectInput in) throws IOException {
        delimiter.read(in);
        if (delimiter.value() == 21) {
            id.read(in);                     // 0
            agent_category.read(in);         // 1
            agent_device.read(in);           // 2
            agent_name.read(in);             // 3
            host.read(in);                   // 4
            interval_category.read(in);      // 5
            interval_clique.read(in);        // 6
            interval_millis.read(in);        // 7
            request_body.read(in);           // 8
            request_content_type.read(in);   // 9
            request_headers.read(in);        // 10
            request_method.read(in);         // 11
            request_params.read(in);         // 12
            request_url.read(in);            // 13
            request_user_agent.read(in);     // 14
            response_body.read(in);          // 15
            response_code.read(in);          // 16
            response_content_type.read(in);  // 17
            response_headers.read(in);       // 18
            response_time_millis.read(in);   // 19
            size_category.read(in);          // 20
            size_request_bytes.read(in);     // 21
            size_response_bytes.read(in);    // 22
        } else throw new RuntimeException("Invalid record delimiter");
    }

    /**
     * Writes all message fields to output stream.
     */
    public void write(ObjectOutput out) throws IOException {
        delimiter.read(21);
        delimiter.write(out);
        id.write(out);                     // 0
        agent_category.write(out);         // 1
        agent_device.write(out);           // 2
        agent_name.write(out);             // 3
        host.write(out);                   // 4
        interval_category.write(out);      // 5
        interval_clique.write(out);        // 6
        interval_millis.write(out);        // 7
        request_body.write(out);           // 8
        request_content_type.write(out);   // 9
        request_headers.write(out);        // 10
        request_method.write(out);         // 11
        request_params.write(out);         // 12
        request_url.write(out);            // 13
        request_user_agent.write(out);     // 14
        response_body.write(out);          // 15
        response_code.write(out);          // 16
        response_content_type.write(out);  // 17
        response_headers.write(out);       // 18
        response_time_millis.write(out);   // 19
        size_category.write(out);          // 20
        size_request_bytes.write(out);     // 21
        size_response_bytes.write(out);    // 22
    }

}
