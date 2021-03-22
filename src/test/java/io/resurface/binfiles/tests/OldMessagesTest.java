// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles.tests;

import io.resurface.binfiles.BinaryHttpMessage;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

/**
 * Tests for reading old binary formats for HTTP messages.
 */
public class OldMessagesTest {

    @Test
    public void read21Test() throws Exception {
        try (FileInputStream fi = new FileInputStream("./src/test/resources/messages-v21.bin")) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(m.id.value()).toEqual("id");                                                           // 0
                    expect(m.agent_category.value()).toEqual("agent_category");                                   // 1
                    expect(m.agent_device.value()).toEqual("agent_device");                                       // 2
                    expect(m.agent_name.value()).toEqual("agent_name");                                           // 3
                    expect(m.graphql_operation.value()).toBeNull();                                               // 4
                    expect(m.graphql_operation_name.value()).toBeNull();                                          // 5
                    expect(m.host.value()).toEqual("host");                                                       // 6
                    expect(m.interval_millis.value()).toEqual(123456);                                            // 7
                    expect(m.request_body.value()).toEqual("request_body");                                       // 8
                    expect(m.request_content_type.value()).toEqual("request_content_type");                       // 9
                    expect(m.request_headers.value()).toEqual("request_headers");                                 // 10
                    expect(m.request_json_type.value()).toBeNull();                                               // 11
                    expect(m.request_method.value()).toEqual("request_method");                                   // 12
                    expect(m.request_params.value()).toEqual("request_params");                                   // 13
                    expect(m.request_url.value()).toEqual("request_url");                                         // 14
                    expect(m.request_user_agent.value()).toEqual("request_user_agent");                           // 15
                    expect(m.response_body.value()).toEqual("response_body");                                     // 16
                    expect(m.response_code.value()).toEqual("response_code");                                     // 17
                    expect(m.response_content_type.value()).toEqual("response_content_type");                     // 18
                    expect(m.response_headers.value()).toEqual("response_headers");                               // 19
                    expect(m.response_json_type.value()).toBeNull();                                              // 20
                    expect(m.response_time_millis.value()).toEqual(1234);                                         // 21
                    expect(m.size_request_bytes.value()).toEqual(23);                                             // 22
                    expect(m.size_response_bytes.value()).toEqual(45);                                            // 23
                    expect(m.length()).toEqual(237);
                }
            }
        }
    }

}
