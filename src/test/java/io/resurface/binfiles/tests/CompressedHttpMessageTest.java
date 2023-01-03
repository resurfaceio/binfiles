// © 2016-2023 Resurface Labs Inc.

package io.resurface.binfiles.tests;

import io.resurface.binfiles.CompressedHttpMessage;
import it.unimi.dsi.fastutil.io.FastBufferedInputStream;
import it.unimi.dsi.fastutil.io.FastBufferedOutputStream;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

/**
 * Tests against compressed binary format for HTTP messages.
 */
public class CompressedHttpMessageTest {

    public final String FILE1 = "./target/message.1.blkc";
    public final String FILE2 = "./target/message.2.blkc";
    public final String FILE3 = "./target/message.3.blkc";
    public final String FILE4 = "./target/message.4.blkc";

    public final byte[] BUFFER = new byte[10000];

    @Test
    public void allNullsTest() throws Exception {
        try (FileOutputStream fo = new FileOutputStream(FILE1)) {
            try (FastBufferedOutputStream bos = new FastBufferedOutputStream(fo)) {
                CompressedHttpMessage m = new CompressedHttpMessage();
                m.write(bos, BUFFER);
                expect(m.bytes()).toEqual(240);
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE1)) {
            try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
                CompressedHttpMessage m = new CompressedHttpMessage();
                m.read(bis);
                expect(m.id.value()).toBeNull();                                                                                   // 0
                expect(m.id.isNull()).toBeTrue();
                expect(m.agent_category.value()).toBeNull();                                                                       // 1
                expect(m.agent_category.isNull()).toBeTrue();
                expect(m.agent_device.value()).toBeNull();                                                                         // 2
                expect(m.agent_device.isNull()).toBeTrue();
                expect(m.agent_name.value()).toBeNull();                                                                           // 3
                expect(m.agent_name.isNull()).toBeTrue();
                expect(m.graphql_operations.value()).toBeNull();                                                                   // 4 (v3)
                expect(m.graphql_operations.isNull()).toBeTrue();
                expect(m.graphql_operations_count.value()).toEqual(0);                                                             // 5 (v3)
                expect(m.host.value()).toBeNull();                                                                                 // 6
                expect(m.host.isNull()).toBeTrue();
                expect(m.interval_millis.value()).toEqual(0);                                                                      // 7
                expect(m.request_body.value()).toBeNull();                                                                         // 8
                expect(m.request_body.isNull()).toBeTrue();
                expect(m.request_content_type.value()).toBeNull();                                                                 // 9
                expect(m.request_content_type.isNull()).toBeTrue();
                expect(m.request_headers.value()).toBeNull();                                                                      // 10
                expect(m.request_headers.isNull()).toBeTrue();
                expect(m.request_json_type.value()).toBeNull();                                                                    // 11
                expect(m.request_json_type.isNull()).toBeTrue();
                expect(m.request_method.value()).toBeNull();                                                                       // 12
                expect(m.request_method.isNull()).toBeTrue();
                expect(m.request_params.value()).toBeNull();                                                                       // 13
                expect(m.request_params.isNull()).toBeTrue();
                expect(m.request_url.value()).toBeNull();                                                                          // 14
                expect(m.request_url.isNull()).toBeTrue();
                expect(m.request_user_agent.value()).toBeNull();                                                                   // 15
                expect(m.request_user_agent.isNull()).toBeTrue();
                expect(m.response_body.value()).toBeNull();                                                                        // 16
                expect(m.response_body.isNull()).toBeTrue();
                expect(m.response_code.value()).toBeNull();                                                                        // 17
                expect(m.response_code.isNull()).toBeTrue();
                expect(m.response_content_type.value()).toBeNull();                                                                // 18
                expect(m.response_content_type.isNull()).toBeTrue();
                expect(m.response_headers.value()).toBeNull();                                                                     // 19
                expect(m.response_headers.isNull()).toBeTrue();
                expect(m.response_json_type.value()).toBeNull();                                                                   // 20
                expect(m.response_json_type.isNull()).toBeTrue();
                expect(m.response_time_millis.value()).toEqual(0);                                                                 // 21
                expect(m.size_request_bytes.value()).toEqual(0);                                                                   // 22
                expect(m.size_response_bytes.value()).toEqual(0);                                                                  // 23
                expect(m.custom_fields.value()).toBeNull();                                                                        // 24 (v3)
                expect(m.custom_fields.isNull()).toBeTrue();
                expect(m.request_address.value()).toBeNull();                                                                      // 25 (v3)
                expect(m.request_address.isNull()).toBeTrue();
                expect(m.session_fields.value()).toBeNull();                                                                       // 26 (v3)
                expect(m.session_fields.isNull()).toBeTrue();
                expect(m.cookies.value()).toBeNull();                                                                              // 27 (v3)
                expect(m.cookies.isNull()).toBeTrue();
                expect(m.cookies_count.value()).toEqual(0);                                                                        // 28 (v3)
                // skip response_status                                                                                            // 29 (v3.1)
                // skip size_total_bytes                                                                                           // 30 (v3.1)
                expect(m.bitmap_versioning.value()).toEqual(0);                                                                    // 31 (v3.1)
                expect(m.bitmap_request_info.value()).toEqual(0);                                                                  // 32 (v3.1)
                expect(m.bitmap_request_json.value()).toEqual(0);                                                                  // 33 (v3.1)
                expect(m.bitmap_request_graphql.value()).toEqual(0);                                                               // 34 (v3.1)
                expect(m.bitmap_request_pii.value()).toEqual(0);                                                                   // 35 (v3.1)
                expect(m.bitmap_request_threat.value()).toEqual(0);                                                                // 36 (v3.1)
                expect(m.bitmap_response_info.value()).toEqual(0);                                                                 // 37 (v3.1)
                expect(m.bitmap_response_json.value()).toEqual(0);                                                                 // 38 (v3.1)
                expect(m.bitmap_response_pii.value()).toEqual(0);                                                                  // 39 (v3.1)
                expect(m.bitmap_response_threat.value()).toEqual(0);                                                               // 40 (v3.1)
                expect(m.bitmap_attack_request.value()).toEqual(0);                                                                // 41 (v3.1)
                expect(m.bitmap_attack_application.value()).toEqual(0);                                                            // 42 (v3.1)
                expect(m.bitmap_attack_injection.value()).toEqual(0);                                                              // 43 (v3.1)
                expect(m.bitmap_response_leak.value()).toEqual(0);                                                                 // 44 (v3.1)
                expect(m.bitmap_unused2.value()).toEqual(0);                                                                       // 45 (v3.1)
                expect(m.bitmap_unused3.value()).toEqual(0);                                                                       // 46 (v3.1)
                expect(m.bitmap_unused4.value()).toEqual(0);                                                                       // 47 (v3.1)
                expect(m.bitmap_unused5.value()).toEqual(0);                                                                       // 48 (v3.1)
                // skip shard_file                                                                                                 // 49 (v3.5)
                expect(m.bytes()).toEqual(240);
            }
        }
    }

    @Test
    public void roundTripTest() throws Exception {
        try (FileOutputStream fo = new FileOutputStream(FILE1)) {
            try (FastBufferedOutputStream bos = new FastBufferedOutputStream(fo)) {
                CompressedHttpMessage m = new CompressedHttpMessage();
                m.id.read("id 😀");                                                                                                // 0
                expect(m.id.isNull()).toBeFalse();
                m.agent_category.read("agent_category 😀");                                                                        // 1
                expect(m.agent_category.isNull()).toBeFalse();
                m.agent_device.read("agent_device 😀");                                                                            // 2
                expect(m.agent_device.isNull()).toBeFalse();
                m.agent_name.read("agent_name 😀");                                                                                // 3
                expect(m.agent_name.isNull()).toBeFalse();
                m.graphql_operations.read("graphql_operations 😀");                                                                // 4 (v3)
                expect(m.graphql_operations.isNull()).toBeFalse();
                m.graphql_operations_count.read(27);                                                                               // 5 (v3)
                m.host.read("host 😀");                                                                                            // 6
                expect(m.host.isNull()).toBeFalse();
                m.interval_millis.read(123456);                                                                                    // 7
                m.request_body.read("request_body 😀");                                                                            // 8
                expect(m.request_body.isNull()).toBeFalse();
                m.request_content_type.read("request_content_type 😀");                                                            // 9
                expect(m.request_content_type.isNull()).toBeFalse();
                m.request_headers.read("request_headers 😀");                                                                      // 10
                expect(m.request_headers.isNull()).toBeFalse();
                m.request_json_type.read("request_json_type 😀");                                                                  // 11
                expect(m.request_json_type.isNull()).toBeFalse();
                m.request_method.read("request_method 😀");                                                                        // 12
                expect(m.request_method.isNull()).toBeFalse();
                m.request_params.read("request_params 😀");                                                                        // 13
                expect(m.request_params.isNull()).toBeFalse();
                m.request_url.read("request_url 😀");                                                                              // 14
                expect(m.request_url.isNull()).toBeFalse();
                m.request_user_agent.read("request_user_agent 😀");                                                                // 15
                expect(m.request_user_agent.isNull()).toBeFalse();
                m.response_body.read("response_body 😀");                                                                          // 16
                expect(m.response_body.isNull()).toBeFalse();
                m.response_code.read("response_code 😀");                                                                          // 17
                expect(m.response_code.isNull()).toBeFalse();
                m.response_content_type.read("response_content_type 😀");                                                          // 18
                expect(m.response_content_type.isNull()).toBeFalse();
                m.response_headers.read("response_headers 😀");                                                                    // 19
                expect(m.response_headers.isNull()).toBeFalse();
                m.response_json_type.read("response_json_type 😀");                                                                // 20
                expect(m.response_json_type.isNull()).toBeFalse();
                m.response_time_millis.read(1234);                                                                                 // 21
                m.size_request_bytes.read(23);                                                                                     // 22
                m.size_response_bytes.read(45);                                                                                    // 23
                m.custom_fields.read("custom_fields 😀");                                                                          // 24 (v3)
                expect(m.custom_fields.isNull()).toBeFalse();
                m.request_address.read("request_address 😀");                                                                      // 25 (v3)
                expect(m.request_address.isNull()).toBeFalse();
                m.session_fields.read("session_fields 😀");                                                                        // 26 (v3)
                expect(m.session_fields.isNull()).toBeFalse();
                m.cookies.read("cookies 😀");                                                                                      // 27 (v3)
                expect(m.cookies.isNull()).toBeFalse();
                m.cookies_count.read(56);                                                                                          // 28 (v3)
                // skip response_status                                                                                            // 29 (v3.1)
                // skip size_total_bytes                                                                                           // 30 (v3.1)
                m.bitmap_versioning.read(31);                                                                                      // 31 (v3.1)
                m.bitmap_request_info.read(32);                                                                                    // 32 (v3.1)
                m.bitmap_request_json.read(33);                                                                                    // 33 (v3.1)
                m.bitmap_request_graphql.read(34);                                                                                 // 34 (v3.1)
                m.bitmap_request_pii.read(35);                                                                                     // 35 (v3.1)
                m.bitmap_request_threat.read(36);                                                                                  // 36 (v3.1)
                m.bitmap_response_info.read(37);                                                                                   // 37 (v3.1)
                m.bitmap_response_json.read(38);                                                                                   // 38 (v3.1)
                m.bitmap_response_pii.read(39);                                                                                    // 39 (v3.1)
                m.bitmap_response_threat.read(40);                                                                                 // 40 (v3.1)
                m.bitmap_attack_request.read(41);                                                                                  // 41 (v3.1)
                m.bitmap_attack_application.read(42);                                                                              // 42 (v3.1)
                m.bitmap_attack_injection.read(43);                                                                                // 43 (v3.1)
                m.bitmap_response_leak.read(44);                                                                                   // 44 (v3.1)
                m.bitmap_unused2.read(45);                                                                                         // 45 (v3.1)
                m.bitmap_unused3.read(46);                                                                                         // 46 (v3.1)
                m.bitmap_unused4.read(47);                                                                                         // 47 (v3.1)
                m.bitmap_unused5.read(48);                                                                                         // 48 (v3.1)
                // skip shard_file                                                                                                 // 49 (v3.5)
                m.write(bos, BUFFER);
                expect(m.bytes()).toEqual(666);
                m.id.read("id2");
                m.write(bos, BUFFER);
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE1)) {
            try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
                CompressedHttpMessage m = new CompressedHttpMessage();
                m.read(bis);
                expect(m.id.value()).toEqual("id 😀");                                                                             // 0
                expect(m.id.isNull()).toBeFalse();
                expect(m.agent_category.value()).toEqual("agent_category 😀");                                                     // 1
                expect(m.agent_category.isNull()).toBeFalse();
                expect(m.agent_device.value()).toEqual("agent_device 😀");                                                         // 2
                expect(m.agent_device.isNull()).toBeFalse();
                expect(m.agent_name.value()).toEqual("agent_name 😀");                                                             // 3
                expect(m.agent_name.isNull()).toBeFalse();
                expect(m.graphql_operations.value()).toEqual("graphql_operations 😀");                                             // 4 (v3)
                expect(m.graphql_operations.isNull()).toBeFalse();
                expect(m.graphql_operations_count.value()).toEqual(27);                                                            // 5 (v3)
                expect(m.host.value()).toEqual("host 😀");                                                                         // 6
                expect(m.host.isNull()).toBeFalse();
                expect(m.interval_millis.value()).toEqual(123456);                                                                 // 7
                expect(m.request_body.value()).toEqual("request_body 😀");                                                         // 8
                expect(m.request_body.isNull()).toBeFalse();
                expect(m.request_content_type.value()).toEqual("request_content_type 😀");                                         // 9
                expect(m.request_content_type.isNull()).toBeFalse();
                expect(m.request_headers.value()).toEqual("request_headers 😀");                                                   // 10
                expect(m.request_headers.isNull()).toBeFalse();
                expect(m.request_json_type.value()).toEqual("request_json_type 😀");                                               // 11
                expect(m.request_json_type.isNull()).toBeFalse();
                expect(m.request_method.value()).toEqual("request_method 😀");                                                     // 12
                expect(m.request_method.isNull()).toBeFalse();
                expect(m.request_params.value()).toEqual("request_params 😀");                                                     // 13
                expect(m.request_params.isNull()).toBeFalse();
                expect(m.request_url.value()).toEqual("request_url 😀");                                                           // 14
                expect(m.request_url.isNull()).toBeFalse();
                expect(m.request_user_agent.value()).toEqual("request_user_agent 😀");                                             // 15
                expect(m.request_user_agent.isNull()).toBeFalse();
                expect(m.response_body.value()).toEqual("response_body 😀");                                                       // 16
                expect(m.response_body.isNull()).toBeFalse();
                expect(m.response_code.value()).toEqual("response_code 😀");                                                       // 17
                expect(m.response_code.isNull()).toBeFalse();
                expect(m.response_content_type.value()).toEqual("response_content_type 😀");                                       // 18
                expect(m.response_content_type.isNull()).toBeFalse();
                expect(m.response_headers.value()).toEqual("response_headers 😀");                                                 // 19
                expect(m.response_headers.isNull()).toBeFalse();
                expect(m.response_json_type.value()).toEqual("response_json_type 😀");                                             // 20
                expect(m.response_json_type.isNull()).toBeFalse();
                expect(m.response_time_millis.value()).toEqual(1234);                                                              // 21
                expect(m.size_request_bytes.value()).toEqual(23);                                                                  // 22
                expect(m.size_response_bytes.value()).toEqual(45);                                                                 // 23
                expect(m.custom_fields.value()).toEqual("custom_fields 😀");                                                       // 24 (v3)
                expect(m.custom_fields.isNull()).toBeFalse();
                expect(m.request_address.value()).toEqual("request_address 😀");                                                   // 25 (v3)
                expect(m.request_address.isNull()).toBeFalse();
                expect(m.session_fields.value()).toEqual("session_fields 😀");                                                     // 26 (v3)
                expect(m.session_fields.isNull()).toBeFalse();
                expect(m.cookies.value()).toEqual("cookies 😀");                                                                   // 27 (v3)
                expect(m.cookies.isNull()).toBeFalse();
                expect(m.cookies_count.value()).toEqual(56);                                                                       // 28 (v3)
                // skip response_status                                                                                            // 29 (v3.1)
                // skip size_total_bytes                                                                                           // 30 (v3.1)
                expect(m.bitmap_versioning.value()).toEqual(31);                                                                   // 31 (v3.1)
                expect(m.bitmap_request_info.value()).toEqual(32);                                                                 // 32 (v3.1)
                expect(m.bitmap_request_json.value()).toEqual(33);                                                                 // 33 (v3.1)
                expect(m.bitmap_request_graphql.value()).toEqual(34);                                                              // 34 (v3.1)
                expect(m.bitmap_request_pii.value()).toEqual(35);                                                                  // 35 (v3.1)
                expect(m.bitmap_request_threat.value()).toEqual(36);                                                               // 36 (v3.1)
                expect(m.bitmap_response_info.value()).toEqual(37);                                                                // 37 (v3.1)
                expect(m.bitmap_response_json.value()).toEqual(38);                                                                // 38 (v3.1)
                expect(m.bitmap_response_pii.value()).toEqual(39);                                                                 // 39 (v3.1)
                expect(m.bitmap_response_threat.value()).toEqual(40);                                                              // 40 (v3.1)
                expect(m.bitmap_attack_request.value()).toEqual(41);                                                               // 41 (v3.1)
                expect(m.bitmap_attack_application.value()).toEqual(42);                                                           // 42 (v3.1)
                expect(m.bitmap_attack_injection.value()).toEqual(43);                                                             // 43 (v3.1)
                expect(m.bitmap_response_leak.value()).toEqual(44);                                                                // 44 (v3.1)
                expect(m.bitmap_unused2.value()).toEqual(45);                                                                      // 45 (v3.1)
                expect(m.bitmap_unused3.value()).toEqual(46);                                                                      // 46 (v3.1)
                expect(m.bitmap_unused4.value()).toEqual(47);                                                                      // 47 (v3.1)
                expect(m.bitmap_unused5.value()).toEqual(48);                                                                      // 48 (v3.1)
                // skip shard_file                                                                                                 // 49 (v3.5)
                expect(m.bytes()).toEqual(666);
                m.read(bis);
                expect(m.id.value()).toEqual("id2");
            }
        }
    }

    public static String LOREM_IPSUM_255 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis sed eros augue. Nunc in mi consequat, convallis elit ut, vestibulum arcu. Pellentesque ultricies orci et tempus porta. Morbi congue iaculis tortor, nec maximus nisi facilisis sit amet placerat.";
    public static String LOREM_IPSUM_256 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ut viverra nulla. Etiam porttitor dignissim finibus. Fusce porttitor, eros a condimentum efficitur, justo ligula finibus mauris, et pretium nunc diam id sapien. Curabitur mattis porta ante.";
    public static String LOREM_IPSUM_512 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend, magna a placerat rutrum, nunc diam volutpat nisl, sed mattis velit justo non enim. Mauris nec fringilla tellus. In laoreet lacinia nibh, non maximus ante sollicitudin et. In sodales vitae est vitae vestibulum. Nunc ornare erat dui, vitae aliquet arcu sollicitudin id. Duis arcu ipsum, pretium sed faucibus eget, egestas a est. Donec nec dolor at arcu pretium tincidunt eu ac tortor. Vestibulum ante ipsum primis in faucibus orci luctus ex.";
    public static String LOREM_IPSUM_768 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus est ac mollis suscipit. Morbi non interdum elit. Nulla ac porta sem, ut lobortis nibh. Morbi volutpat vitae metus nec blandit. Mauris nisl sapien, facilisis nec placerat et, vehicula eget mi. Nunc ac augue cursus, faucibus sem ut, porta dolor. Sed varius viverra massa. Donec fermentum, velit at molestie semper, mauris felis tempor est, egestas ultricies lacus ipsum eget nunc. Donec ultrices viverra tortor, iaculis auctor dolor convallis vel. Maecenas turpis libero, pretium at pretium sed, dictum ut purus. Donec ac maximus enim. Aliquam porttitor lorem ut nibh pellentesque ultrices. Quisque ut ipsum dapibus arcu tincidunt ullamcorper eu sed nisi. Suspendisse ut urna non nunc fringilla ac.";

    @Test
    public void largeRoundTripTest() throws Exception {
        try (FileOutputStream fo = new FileOutputStream(FILE2)) {
            try (FastBufferedOutputStream bos = new FastBufferedOutputStream(fo)) {
                CompressedHttpMessage m = new CompressedHttpMessage();
                m.id.read("id");                                                                                                   // 0
                m.agent_category.read("agent_category");                                                                           // 1
                m.agent_device.read("agent_device");                                                                               // 2
                m.agent_name.read("agent_name");                                                                                   // 3
                m.graphql_operations.read("graphql_operations");                                                                   // 4 (v3)
                m.graphql_operations_count.read(27);                                                                               // 5 (v3)
                m.host.read("host");                                                                                               // 6
                m.interval_millis.read(123456);                                                                                    // 7
                m.request_body.read(LOREM_IPSUM_255);                                                                              // 8
                m.request_content_type.read("request_content_type");                                                               // 9
                m.request_headers.read(LOREM_IPSUM_512);                                                                           // 10
                m.request_json_type.read("request_json_type");                                                                     // 11
                m.request_method.read("request_method");                                                                           // 12
                m.request_params.read("request_params");                                                                           // 13
                m.request_url.read("request_url");                                                                                 // 14
                m.request_user_agent.read("request_user_agent");                                                                   // 15
                m.response_body.read(LOREM_IPSUM_768);                                                                             // 16
                m.response_code.read("200");                                                                                       // 17
                m.response_content_type.read("response_content_type");                                                             // 18
                m.response_headers.read(LOREM_IPSUM_256);                                                                          // 19
                m.response_json_type.read("response_json_type");                                                                   // 20
                m.response_time_millis.read(1234);                                                                                 // 21
                m.size_request_bytes.read(23);                                                                                     // 22
                m.size_response_bytes.read(45);                                                                                    // 23
                m.custom_fields.read("custom_fields");                                                                             // 24 (v3)
                m.request_address.read("request_address");                                                                         // 25 (v3)
                m.session_fields.read("session_fields");                                                                           // 26 (v3)
                m.cookies.read("cookies");                                                                                         // 27 (v3)
                m.cookies_count.read(56);                                                                                          // 28 (v3)
                // skip response_status                                                                                            // 29 (v3.1)
                // skip size_total_bytes                                                                                           // 30 (v3.1)
                m.bitmap_versioning.read(31);                                                                                      // 31 (v3.1)
                m.bitmap_request_info.read(32);                                                                                    // 32 (v3.1)
                m.bitmap_request_json.read(33);                                                                                    // 33 (v3.1)
                m.bitmap_request_graphql.read(34);                                                                                 // 34 (v3.1)
                m.bitmap_request_pii.read(35);                                                                                     // 35 (v3.1)
                m.bitmap_request_threat.read(36);                                                                                  // 36 (v3.1)
                m.bitmap_response_info.read(37);                                                                                   // 37 (v3.1)
                m.bitmap_response_json.read(38);                                                                                   // 38 (v3.1)
                m.bitmap_response_pii.read(39);                                                                                    // 39 (v3.1)
                m.bitmap_response_threat.read(40);                                                                                 // 40 (v3.1)
                m.bitmap_attack_request.read(41);                                                                                  // 41 (v3.1)
                m.bitmap_attack_application.read(42);                                                                              // 42 (v3.1)
                m.bitmap_attack_injection.read(43);                                                                                // 43 (v3.1)
                m.bitmap_response_leak.read(44);                                                                                   // 44 (v3.1)
                m.bitmap_unused2.read(45);                                                                                         // 45 (v3.1)
                m.bitmap_unused3.read(46);                                                                                         // 46 (v3.1)
                m.bitmap_unused4.read(47);                                                                                         // 47 (v3.1)
                m.bitmap_unused5.read(48);                                                                                         // 48 (v3.1)
                // skip shard_file                                                                                                 // 49 (v3.5)
                m.write(bos, BUFFER);
                expect(m.bytes()).toEqual(2119);
                m.id.read("id2");
                m.write(bos, BUFFER);
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE2)) {
            try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
                CompressedHttpMessage m = new CompressedHttpMessage();
                m.read(bis);
                expect(m.id.value()).toEqual("id");                                                                                // 0
                expect(m.agent_category.value()).toEqual("agent_category");                                                        // 1
                expect(m.agent_device.value()).toEqual("agent_device");                                                            // 2
                expect(m.agent_name.value()).toEqual("agent_name");                                                                // 3
                expect(m.graphql_operations.value()).toEqual("graphql_operations");                                                // 4 (v3)
                expect(m.graphql_operations_count.value()).toEqual(27);                                                            // 5 (v3)
                expect(m.host.value()).toEqual("host");                                                                            // 6
                expect(m.interval_millis.value()).toEqual(123456);                                                                 // 7
                expect(m.request_body.value()).toEqual(LOREM_IPSUM_255);                                                           // 8
                expect(m.request_content_type.value()).toEqual("request_content_type");                                            // 9
                expect(m.request_headers.value()).toEqual(LOREM_IPSUM_512);                                                        // 10
                expect(m.request_json_type.value()).toEqual("request_json_type");                                                  // 11
                expect(m.request_method.value()).toEqual("request_method");                                                        // 12
                expect(m.request_params.value()).toEqual("request_params");                                                        // 13
                expect(m.request_url.value()).toEqual("request_url");                                                              // 14
                expect(m.request_user_agent.value()).toEqual("request_user_agent");                                                // 15
                expect(m.response_body.value()).toEqual(LOREM_IPSUM_768);                                                          // 16
                expect(m.response_code.value()).toEqual("200");                                                                    // 17
                expect(m.response_content_type.value()).toEqual("response_content_type");                                          // 18
                expect(m.response_headers.value()).toEqual(LOREM_IPSUM_256);                                                       // 19
                expect(m.response_json_type.value()).toEqual("response_json_type");                                                // 20
                expect(m.response_time_millis.value()).toEqual(1234);                                                              // 21
                expect(m.size_request_bytes.value()).toEqual(23);                                                                  // 22
                expect(m.size_response_bytes.value()).toEqual(45);                                                                 // 23
                expect(m.custom_fields.value()).toEqual("custom_fields");                                                          // 24 (v3)
                expect(m.request_address.value()).toEqual("request_address");                                                      // 25 (v3)
                expect(m.session_fields.value()).toEqual("session_fields");                                                        // 26 (v3)
                expect(m.cookies.value()).toEqual("cookies");                                                                      // 27 (v3)
                expect(m.cookies_count.value()).toEqual(56);                                                                       // 28 (v3)
                // skip response_status                                                                                            // 29 (v3.1)
                // skip size_total_bytes                                                                                           // 30 (v3.1)
                expect(m.bitmap_versioning.value()).toEqual(31);                                                                   // 31 (v3.1)
                expect(m.bitmap_request_info.value()).toEqual(32);                                                                 // 32 (v3.1)
                expect(m.bitmap_request_json.value()).toEqual(33);                                                                 // 33 (v3.1)
                expect(m.bitmap_request_graphql.value()).toEqual(34);                                                              // 34 (v3.1)
                expect(m.bitmap_request_pii.value()).toEqual(35);                                                                  // 35 (v3.1)
                expect(m.bitmap_request_threat.value()).toEqual(36);                                                               // 36 (v3.1)
                expect(m.bitmap_response_info.value()).toEqual(37);                                                                // 37 (v3.1)
                expect(m.bitmap_response_json.value()).toEqual(38);                                                                // 38 (v3.1)
                expect(m.bitmap_response_pii.value()).toEqual(39);                                                                 // 39 (v3.1)
                expect(m.bitmap_response_threat.value()).toEqual(40);                                                              // 40 (v3.1)
                expect(m.bitmap_attack_request.value()).toEqual(41);                                                               // 41 (v3.1)
                expect(m.bitmap_attack_application.value()).toEqual(42);                                                           // 42 (v3.1)
                expect(m.bitmap_attack_injection.value()).toEqual(43);                                                             // 43 (v3.1)
                expect(m.bitmap_response_leak.value()).toEqual(44);                                                                // 44 (v3.1)
                expect(m.bitmap_unused2.value()).toEqual(45);                                                                      // 45 (v3.1)
                expect(m.bitmap_unused3.value()).toEqual(46);                                                                      // 46 (v3.1)
                expect(m.bitmap_unused4.value()).toEqual(47);                                                                      // 47 (v3.1)
                expect(m.bitmap_unused5.value()).toEqual(48);                                                                      // 48 (v3.1)
                // skip shard_file                                                                                                 // 49 (v3.5)
                expect(m.bytes()).toEqual(2119);
                m.read(bis);
                expect(m.id.value()).toEqual("id2");
            }
        }
    }

//    @Test
//    public void copyRoundTripTest() throws Exception {
//        try (FileOutputStream fo = new FileOutputStream(FILE3)) {
//            try (FastBufferedOutputStream bos = new FastBufferedOutputStream(fo)) {
//                CompressedHttpMessage m = new CompressedHttpMessage();
//                m.id.read("id");                                                                                                   // 0
//                m.agent_category.read("agent_category");                                                                           // 1
//                m.agent_device.read("agent_device");                                                                               // 2
//                m.agent_name.read("agent_name");                                                                                   // 3
//                m.graphql_operations.read("graphql_operations");                                                                   // 4 (v3)
//                m.graphql_operations_count.read(27);                                                                               // 5 (v3)
//                m.host.read("host");                                                                                               // 6
//                m.interval_millis.read(123456);                                                                                    // 7
//                m.request_body.read(LOREM_IPSUM_255);                                                                              // 8
//                m.request_content_type.read("request_content_type");                                                               // 9
//                m.request_headers.read(LOREM_IPSUM_512);                                                                           // 10
//                m.request_json_type.read("request_json_type");                                                                     // 11
//                m.request_method.read("request_method");                                                                           // 12
//                m.request_params.read("request_params");                                                                           // 13
//                m.request_url.read("request_url");                                                                                 // 14
//                m.request_user_agent.read("request_user_agent");                                                                   // 15
//                m.response_body.read(LOREM_IPSUM_768);                                                                             // 16
//                m.response_code.read("200");                                                                                       // 17
//                m.response_content_type.read("response_content_type");                                                             // 18
//                m.response_headers.read(LOREM_IPSUM_256);                                                                          // 19
//                m.response_json_type.read("response_json_type");                                                                   // 20
//                m.response_time_millis.read(1234);                                                                                 // 21
//                m.size_request_bytes.read(23);                                                                                     // 22
//                m.size_response_bytes.read(45);                                                                                    // 23
//                m.custom_fields.read("custom_fields");                                                                             // 24 (v3)
//                m.request_address.read("request_address");                                                                         // 25 (v3)
//                m.session_fields.read("session_fields");                                                                           // 26 (v3)
//                m.cookies.read("cookies");                                                                                         // 27 (v3)
//                m.cookies_count.read(56);                                                                                          // 28 (v3)
//                // skip response_status                                                                                            // 29 (v3.1)
//                // skip size_total_bytes                                                                                           // 30 (v3.1)
//                m.bitmap_versioning.read(31);                                                                                      // 31 (v3.1)
//                m.bitmap_request_info.read(32);                                                                                    // 32 (v3.1)
//                m.bitmap_request_json.read(33);                                                                                    // 33 (v3.1)
//                m.bitmap_request_graphql.read(34);                                                                                 // 34 (v3.1)
//                m.bitmap_request_pii.read(35);                                                                                     // 35 (v3.1)
//                m.bitmap_request_threat.read(36);                                                                                  // 36 (v3.1)
//                m.bitmap_response_info.read(37);                                                                                   // 37 (v3.1)
//                m.bitmap_response_json.read(38);                                                                                   // 38 (v3.1)
//                m.bitmap_response_pii.read(39);                                                                                    // 39 (v3.1)
//                m.bitmap_response_threat.read(40);                                                                                 // 40 (v3.1)
//                m.bitmap_attack_request.read(41);                                                                                  // 41 (v3.1)
//                m.bitmap_attack_application.read(42);                                                                              // 42 (v3.1)
//                m.bitmap_attack_injection.read(43);                                                                                // 43 (v3.1)
//                m.bitmap_response_leak.read(44);                                                                                   // 44 (v3.1)
//                m.bitmap_unused2.read(45);                                                                                         // 45 (v3.1)
//                m.bitmap_unused3.read(46);                                                                                         // 46 (v3.1)
//                m.bitmap_unused4.read(47);                                                                                         // 47 (v3.1)
//                m.bitmap_unused5.read(48);                                                                                         // 48 (v3.1)
//                // skip shard_file                                                                                                 // 49 (v3.5)
//                m.write(bos, BUFFER);
//                expect(m.bytes()).toEqual(2119);
//            }
//        }
//
//        try (FileOutputStream fo = new FileOutputStream(FILE4)) {
//            try (FastBufferedOutputStream bos = new FastBufferedOutputStream(fo)) {
//
//                try (FileInputStream fi = new FileInputStream(FILE3)) {
//                    try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
//                        CompressedHttpMessage m = new CompressedHttpMessage();
//                        m.read(bis);
//                        System.out.println("id.value is " + m.id.value());
//                        System.out.println("id.offset is " + m.id.offset());
//                        System.out.println("id.length is " + m.id.length());
//                        System.out.println("id.stored is " + m.id.stored());
//                        m.id.read(m.id.value());
//                        m.write(bos, BUFFER);
//                    }
//                }
//
//            }
//        }
//
//        System.out.println("---------------------------------");
//
//        try (FileInputStream fi = new FileInputStream(FILE4)) {
//            try (FastBufferedInputStream bis = new FastBufferedInputStream(fi)) {
//                CompressedHttpMessage m = new CompressedHttpMessage();
//                m.read(bis);
//                System.out.println("id.value is " + m.id.value());
//                System.out.println("id.offset is " + m.id.offset());
//                System.out.println("id.length is " + m.id.length());
//                System.out.println("id.stored is " + m.id.stored());
//                expect(m.id.value()).toEqual("id");                                                                                // 0
//                expect(m.agent_category.value()).toEqual("agent_category");                                                        // 1
//                expect(m.agent_device.value()).toEqual("agent_device");                                                            // 2
//                expect(m.agent_name.value()).toEqual("agent_name");                                                                // 3
//                expect(m.graphql_operations.value()).toEqual("graphql_operations");                                                // 4 (v3)
//                expect(m.graphql_operations_count.value()).toEqual(27);                                                            // 5 (v3)
//                expect(m.host.value()).toEqual("host");                                                                            // 6
//                expect(m.interval_millis.value()).toEqual(123456);                                                                 // 7
//                expect(m.request_body.value()).toEqual(LOREM_IPSUM_255);                                                           // 8
//                expect(m.request_content_type.value()).toEqual("request_content_type");                                            // 9
//                expect(m.request_headers.value()).toEqual(LOREM_IPSUM_512);                                                        // 10
//                expect(m.request_json_type.value()).toEqual("request_json_type");                                                  // 11
//                expect(m.request_method.value()).toEqual("request_method");                                                        // 12
//                expect(m.request_params.value()).toEqual("request_params");                                                        // 13
//                expect(m.request_url.value()).toEqual("request_url");                                                              // 14
//                expect(m.request_user_agent.value()).toEqual("request_user_agent");                                                // 15
//                expect(m.response_body.value()).toEqual(LOREM_IPSUM_768);                                                          // 16
//                expect(m.response_code.value()).toEqual("200");                                                                    // 17
//                expect(m.response_content_type.value()).toEqual("response_content_type");                                          // 18
//                expect(m.response_headers.value()).toEqual(LOREM_IPSUM_256);                                                       // 19
//                expect(m.response_json_type.value()).toEqual("response_json_type");                                                // 20
//                expect(m.response_time_millis.value()).toEqual(1234);                                                              // 21
//                expect(m.size_request_bytes.value()).toEqual(23);                                                                  // 22
//                expect(m.size_response_bytes.value()).toEqual(45);                                                                 // 23
//                expect(m.custom_fields.value()).toEqual("custom_fields");                                                          // 24 (v3)
//                expect(m.request_address.value()).toEqual("request_address");                                                      // 25 (v3)
//                expect(m.session_fields.value()).toEqual("session_fields");                                                        // 26 (v3)
//                expect(m.cookies.value()).toEqual("cookies");                                                                      // 27 (v3)
//                expect(m.cookies_count.value()).toEqual(56);                                                                       // 28 (v3)
//                // skip response_status                                                                                            // 29 (v3.1)
//                // skip size_total_bytes                                                                                           // 30 (v3.1)
//                expect(m.bitmap_versioning.value()).toEqual(31);                                                                   // 31 (v3.1)
//                expect(m.bitmap_request_info.value()).toEqual(32);                                                                 // 32 (v3.1)
//                expect(m.bitmap_request_json.value()).toEqual(33);                                                                 // 33 (v3.1)
//                expect(m.bitmap_request_graphql.value()).toEqual(34);                                                              // 34 (v3.1)
//                expect(m.bitmap_request_pii.value()).toEqual(35);                                                                  // 35 (v3.1)
//                expect(m.bitmap_request_threat.value()).toEqual(36);                                                               // 36 (v3.1)
//                expect(m.bitmap_response_info.value()).toEqual(37);                                                                // 37 (v3.1)
//                expect(m.bitmap_response_json.value()).toEqual(38);                                                                // 38 (v3.1)
//                expect(m.bitmap_response_pii.value()).toEqual(39);                                                                 // 39 (v3.1)
//                expect(m.bitmap_response_threat.value()).toEqual(40);                                                              // 40 (v3.1)
//                expect(m.bitmap_attack_request.value()).toEqual(41);                                                               // 41 (v3.1)
//                expect(m.bitmap_attack_application.value()).toEqual(42);                                                           // 42 (v3.1)
//                expect(m.bitmap_attack_injection.value()).toEqual(43);                                                             // 43 (v3.1)
//                expect(m.bitmap_response_leak.value()).toEqual(44);                                                                // 44 (v3.1)
//                expect(m.bitmap_unused2.value()).toEqual(45);                                                                      // 45 (v3.1)
//                expect(m.bitmap_unused3.value()).toEqual(46);                                                                      // 46 (v3.1)
//                expect(m.bitmap_unused4.value()).toEqual(47);                                                                      // 47 (v3.1)
//                expect(m.bitmap_unused5.value()).toEqual(48);                                                                      // 48 (v3.1)
//                  // skip shard_file                                                                                                     // 49 (v3.5)
//                expect(m.bytes()).toEqual(2119);
//            }
//        }
//    }

}
