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
        try (FileInputStream fi = new FileInputStream("./src/test/resources/message-v30.blk")) {
            try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
                BinaryHttpMessage m = new BinaryHttpMessage();
                m.read(bis);
                m.id.read("id");                                                                             // 0
                m.agent_category.read("agent_category");                                                     // 1
                m.agent_device.read("agent_device");                                                         // 2
                m.agent_name.read("agent_name");                                                             // 3
                m.graphql_operations.read("graphql_operations");                                             // 4 (v3)
                m.graphql_operations_count.read(27);                                                         // 5 (v3)
                m.host.read("host");                                                                         // 6
                m.interval_millis.read(123456);                                                              // 7
                m.request_body.read("request_body");                                                         // 8
                m.request_content_type.read("request_content_type");                                         // 9
                m.request_headers.read("request_headers");                                                   // 10
                m.request_json_type.read("request_json_type");                                               // 11
                m.request_method.read("request_method");                                                     // 12
                m.request_params.read("request_params");                                                     // 13
                m.request_url.read("request_url");                                                           // 14
                m.request_user_agent.read("request_user_agent");                                             // 15
                m.response_body.read("response_body");                                                       // 16
                m.response_code.read("response_code");                                                       // 17
                m.response_content_type.read("response_content_type");                                       // 18
                m.response_headers.read("response_headers");                                                 // 19
                m.response_json_type.read("response_json_type");                                             // 20
                m.response_time_millis.read(1234);                                                           // 21
                m.size_request_bytes.read(23);                                                               // 22
                m.size_response_bytes.read(45);                                                              // 23
                m.custom_fields.read("custom_fields");                                                       // 24 (v3)
                m.request_address.read("request_address");                                                   // 25 (v3)
                m.session_fields.read("session_fields");                                                     // 26 (v3)
                m.cookies.read("cookies");                                                                   // 27 (v3)
                m.cookies_count.read(56);                                                                    // 28 (v3)
                // skip response_status                                                                      // 29 (v3.1)
                // skip size_total_bytes                                                                     // 30 (v3.1)
                expect(m.bitmap_categories.value()).toEqual(0);                                              // 31 (v3.1)
                expect(m.bitmap_request_info.value()).toEqual(0);                                            // 32 (v3.1)
                expect(m.bitmap_request_json.value()).toEqual(0);                                            // 33 (v3.1)
                expect(m.bitmap_request_graphql.value()).toEqual(0);                                         // 34 (v3.1)
                expect(m.bitmap_request_pii.value()).toEqual(0);                                             // 35 (v3.1)
                expect(m.bitmap_request_quality.value()).toEqual(0);                                         // 36 (v3.1)
                expect(m.bitmap_response_info.value()).toEqual(0);                                           // 37 (v3.1)
                expect(m.bitmap_response_json.value()).toEqual(0);                                           // 38 (v3.1)
                expect(m.bitmap_response_pii.value()).toEqual(0);                                            // 39 (v3.1)
                expect(m.bitmap_response_quality.value()).toEqual(0);                                        // 40 (v3.1)
                expect(m.bitmap_attack_request.value()).toEqual(0);                                          // 41 (v3.1)
                expect(m.bitmap_attack_application.value()).toEqual(0);                                      // 42 (v3.1)
                expect(m.bitmap_attack_injection.value()).toEqual(0);                                        // 43 (v3.1)
                expect(m.bitmap_unused1.value()).toEqual(0);                                                 // 44 (v3.1)
                expect(m.bitmap_unused2.value()).toEqual(0);                                                 // 45 (v3.1)
                expect(m.bitmap_unused3.value()).toEqual(0);                                                 // 46 (v3.1)
                m.read(bis);
                m.id.read("id2");                                                                            // 0
            }
        }
    }

}
