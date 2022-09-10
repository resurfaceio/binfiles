// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles.tests;

import io.resurface.binfiles.BinaryHttpMessage;
import it.unimi.dsi.fastutil.io.FastBufferedInputStream;
import org.junit.Test;

import java.io.FileInputStream;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

/**
 * Tests against old binary formats for HTTP messages.
 */
public class OldBinaryHttpMessageTest {

    @Test
    public void read30Test() throws Exception {
        try (FileInputStream fi = new FileInputStream("./src/test/resources/message-v30.1.blk")) {
            try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
                BinaryHttpMessage m = new BinaryHttpMessage();
                m.read(bis);
                expect(m.id.value()).toEqual("id");                                                          // 0
                expect(m.agent_category.value()).toEqual("agent_category");                                  // 1
                expect(m.agent_device.value()).toEqual("agent_device");                                      // 2
                expect(m.agent_name.value()).toEqual("agent_name");                                          // 3
                expect(m.graphql_operations.value()).toEqual("graphql_operations");                          // 4 (v3)
                expect(m.graphql_operations_count.value()).toEqual(27);                                      // 5 (v3)
                expect(m.host.value()).toEqual("host");                                                      // 6
                expect(m.interval_millis.value()).toEqual(123456);                                           // 7
                expect(m.request_body.value()).toEqual("request_body");                                      // 8
                expect(m.request_content_type.value()).toEqual("request_content_type");                      // 9
                expect(m.request_headers.value()).toEqual("request_headers");                                // 10
                expect(m.request_json_type.value()).toEqual("request_json_type");                            // 11
                expect(m.request_method.value()).toEqual("request_method");                                  // 12
                expect(m.request_params.value()).toEqual("request_params");                                  // 13
                expect(m.request_url.value()).toEqual("request_url");                                        // 14
                expect(m.request_user_agent.value()).toEqual("request_user_agent");                          // 15
                expect(m.response_body.value()).toEqual("response_body");                                    // 16
                expect(m.response_code.value()).toEqual("response_code");                                    // 17
                expect(m.response_content_type.value()).toEqual("response_content_type");                    // 18
                expect(m.response_headers.value()).toEqual("response_headers");                              // 19
                expect(m.response_json_type.value()).toEqual("response_json_type");                          // 20
                expect(m.response_time_millis.value()).toEqual(1234);                                        // 21
                expect(m.size_request_bytes.value()).toEqual(23);                                            // 22
                expect(m.size_response_bytes.value()).toEqual(45);                                           // 23
                expect(m.custom_fields.value()).toEqual("custom_fields");                                    // 24 (v3)
                expect(m.request_address.value()).toEqual("request_address");                                // 25 (v3)
                expect(m.session_fields.value()).toEqual("session_fields");                                  // 26 (v3)
                expect(m.cookies.value()).toEqual("cookies");                                                // 27 (v3)
                expect(m.cookies_count.value()).toEqual(56);                                                 // 28 (v3)
                // skip response_status                                                                      // 29 (v3.1)
                // skip size_total_bytes                                                                     // 30 (v3.1)
                expect(m.bitmap_versioning.value()).toEqual(0);                                              // 31 (v3.1)
                expect(m.bitmap_request_info.value()).toEqual(0);                                            // 32 (v3.1)
                expect(m.bitmap_request_json.value()).toEqual(0);                                            // 33 (v3.1)
                expect(m.bitmap_request_graphql.value()).toEqual(0);                                         // 34 (v3.1)
                expect(m.bitmap_request_pii.value()).toEqual(0);                                             // 35 (v3.1)
                expect(m.bitmap_request_threat.value()).toEqual(0);                                          // 36 (v3.1)
                expect(m.bitmap_response_info.value()).toEqual(0);                                           // 37 (v3.1)
                expect(m.bitmap_response_json.value()).toEqual(0);                                           // 38 (v3.1)
                expect(m.bitmap_response_pii.value()).toEqual(0);                                            // 39 (v3.1)
                expect(m.bitmap_response_threat.value()).toEqual(0);                                         // 40 (v3.1)
                expect(m.bitmap_attack_request.value()).toEqual(0);                                          // 41 (v3.1)
                expect(m.bitmap_attack_application.value()).toEqual(0);                                      // 42 (v3.1)
                expect(m.bitmap_attack_injection.value()).toEqual(0);                                        // 43 (v3.1)
                expect(m.bitmap_response_leak.value()).toEqual(0);                                           // 44 (v3.1)
                expect(m.bitmap_unused2.value()).toEqual(0);                                                 // 45 (v3.1)
                expect(m.bitmap_unused3.value()).toEqual(0);                                                 // 46 (v3.1)
                expect(m.bitmap_unused4.value()).toEqual(0);                                                 // 47 (v3.1)
                expect(m.bitmap_unused5.value()).toEqual(0);                                                 // 48 (v3.1)
                m.read(bis);
                m.id.read("id2");                                                                            // 0
            }
        }
    }

}
