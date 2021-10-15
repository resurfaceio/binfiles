// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles.tests;

import io.resurface.binfiles.BinaryHttpMessage;
import org.junit.Test;

import java.io.*;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

/**
 * Tests against binary format for HTTP messages.
 */
public class BinaryHttpMessageTest {

    public final String FILE = "./target/messages.bin";

    @Test
    public void allNullsTest() throws Exception {
        try (FileOutputStream fo = new FileOutputStream(FILE)) {
            try (BufferedOutputStream bos = new BufferedOutputStream(fo)) {
                try (ObjectOutputStream so = new ObjectOutputStream(bos)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.write(so);
                }
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(m.id.value()).toBeNull();                                                              // 0
                    expect(m.agent_category.value()).toBeNull();                                                  // 1
                    expect(m.agent_device.value()).toBeNull();                                                    // 2
                    expect(m.agent_name.value()).toBeNull();                                                      // 3
                    expect(m.graphql_operation.value()).toBeNull();                                               // 4
                    expect(m.graphql_operation_name.value()).toBeNull();                                          // 5
                    expect(m.host.value()).toBeNull();                                                            // 6
                    expect(m.interval_millis.value()).toEqual(0);                                                 // 7
                    expect(m.request_body.value()).toBeNull();                                                    // 8
                    expect(m.request_content_type.value()).toBeNull();                                            // 9
                    expect(m.request_headers.value()).toBeNull();                                                 // 10
                    expect(m.request_json_type.value()).toBeNull();                                               // 11
                    expect(m.request_method.value()).toBeNull();                                                  // 12
                    expect(m.request_params.value()).toBeNull();                                                  // 13
                    expect(m.request_url.value()).toBeNull();                                                     // 14
                    expect(m.request_user_agent.value()).toBeNull();                                              // 15
                    expect(m.response_body.value()).toBeNull();                                                   // 16
                    expect(m.response_code.value()).toBeNull();                                                   // 17
                    expect(m.response_content_type.value()).toBeNull();                                           // 18
                    expect(m.response_headers.value()).toBeNull();                                                // 19
                    expect(m.response_json_type.value()).toBeNull();                                              // 20
                    expect(m.response_time_millis.value()).toEqual(0);                                            // 21
                    expect(m.size_request_bytes.value()).toEqual(0);                                              // 22
                    expect(m.size_response_bytes.value()).toEqual(0);                                             // 23
                    expect(m.custom_fields.value()).toBeNull();                                                   // 24 (v3)
                    expect(m.request_address.value()).toBeNull();                                                 // 25 (v3)
                    expect(m.session_fields.value()).toBeNull();                                                  // 26 (v3)
                    expect(m.length()).toEqual(28);
                }
            }
        }
    }

    @Test
    public void roundTripTest() throws Exception {
        try (FileOutputStream fo = new FileOutputStream(FILE)) {
            try (BufferedOutputStream bos = new BufferedOutputStream(fo)) {
                try (ObjectOutputStream so = new ObjectOutputStream(bos)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.id.read("id");                                                                              // 0
                    m.agent_category.read("agent_category");                                                      // 1
                    m.agent_device.read("agent_device");                                                          // 2
                    m.agent_name.read("agent_name");                                                              // 3
                    m.graphql_operation.read("graphql_operation");                                                // 4
                    m.graphql_operation_name.read("graphql_operation_name");                                      // 5
                    m.host.read("host");                                                                          // 6
                    m.interval_millis.read(123456);                                                               // 7
                    m.request_body.read("request_body");                                                          // 8
                    m.request_content_type.read("request_content_type");                                          // 9
                    m.request_headers.read("request_headers");                                                    // 10
                    m.request_json_type.read("request_json_type");                                                // 11
                    m.request_method.read("request_method");                                                      // 12
                    m.request_params.read("request_params");                                                      // 13
                    m.request_url.read("request_url");                                                            // 14
                    m.request_user_agent.read("request_user_agent");                                              // 15
                    m.response_body.read("response_body");                                                        // 16
                    m.response_code.read("response_code");                                                        // 17
                    m.response_content_type.read("response_content_type");                                        // 18
                    m.response_headers.read("response_headers");                                                  // 19
                    m.response_json_type.read("response_json_type");                                              // 20
                    m.response_time_millis.read(1234);                                                            // 21
                    m.size_request_bytes.read(23);                                                                // 22
                    m.size_response_bytes.read(45);                                                               // 23
                    m.custom_fields.read("custom_fields");                                                        // 24 (v3)
                    m.request_address.read("request_address");                                                    // 25 (v3)
                    m.session_fields.read("session_fields");                                                      // 26 (v3)
                    m.write(so);
                }
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(m.id.value()).toEqual("id");                                                           // 0
                    expect(m.agent_category.value()).toEqual("agent_category");                                   // 1
                    expect(m.agent_device.value()).toEqual("agent_device");                                       // 2
                    expect(m.agent_name.value()).toEqual("agent_name");                                           // 3
                    expect(m.graphql_operation.value()).toEqual("graphql_operation");                             // 4
                    expect(m.graphql_operation_name.value()).toEqual("graphql_operation_name");                   // 5
                    expect(m.host.value()).toEqual("host");                                                       // 6
                    expect(m.interval_millis.value()).toEqual(123456);                                            // 7
                    expect(m.request_body.value()).toEqual("request_body");                                       // 8
                    expect(m.request_content_type.value()).toEqual("request_content_type");                       // 9
                    expect(m.request_headers.value()).toEqual("request_headers");                                 // 10
                    expect(m.request_json_type.value()).toEqual("request_json_type");                             // 11
                    expect(m.request_method.value()).toEqual("request_method");                                   // 12
                    expect(m.request_params.value()).toEqual("request_params");                                   // 13
                    expect(m.request_url.value()).toEqual("request_url");                                         // 14
                    expect(m.request_user_agent.value()).toEqual("request_user_agent");                           // 15
                    expect(m.response_body.value()).toEqual("response_body");                                     // 16
                    expect(m.response_code.value()).toEqual("response_code");                                     // 17
                    expect(m.response_content_type.value()).toEqual("response_content_type");                     // 18
                    expect(m.response_headers.value()).toEqual("response_headers");                               // 19
                    expect(m.response_json_type.value()).toEqual("response_json_type");                           // 20
                    expect(m.response_time_millis.value()).toEqual(1234);                                         // 21
                    expect(m.size_request_bytes.value()).toEqual(23);                                             // 22
                    expect(m.size_response_bytes.value()).toEqual(45);                                            // 23
                    expect(m.custom_fields.value()).toEqual("custom_fields");                                     // 24 (v3)
                    expect(m.request_address.value()).toEqual("request_address");                                 // 25 (v3)
                    expect(m.session_fields.value()).toEqual("session_fields");                                   // 26 (v3)
                    expect(m.length()).toEqual(353);
                }
            }
        }
    }

}
