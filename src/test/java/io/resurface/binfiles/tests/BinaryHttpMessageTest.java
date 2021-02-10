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
                    expect(m.id.value()).toBeNull();                     // 0
                    expect(m.agent_category.value()).toBeNull();         // 1
                    expect(m.agent_device.value()).toBeNull();           // 2
                    expect(m.agent_name.value()).toBeNull();             // 3
                    expect(m.host.value()).toBeNull();                   // 4
                    expect(m.interval_category.value()).toBeNull();      // 5
                    expect(m.interval_clique.value()).toBeNull();        // 6
                    expect(m.interval_millis.value()).toEqual(0);        // 7
                    expect(m.request_body.value()).toBeNull();           // 8
                    expect(m.request_content_type.value()).toBeNull();   // 9
                    expect(m.request_headers.value()).toBeNull();        // 10
                    expect(m.request_method.value()).toBeNull();         // 11
                    expect(m.request_params.value()).toBeNull();         // 12
                    expect(m.request_url.value()).toBeNull();            // 13
                    expect(m.request_user_agent.value()).toBeNull();     // 14
                    expect(m.response_body.value()).toBeNull();          // 15
                    expect(m.response_code.value()).toBeNull();          // 16
                    expect(m.response_content_type.value()).toBeNull();  // 17
                    expect(m.response_headers.value()).toBeNull();       // 18
                    expect(m.response_time_millis.value()).toEqual(0);   // 19
                    expect(m.size_category.value()).toBeNull();          // 20
                    expect(m.size_request_bytes.value()).toEqual(0);     // 21
                    expect(m.size_response_bytes.value()).toEqual(0);    // 22
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
                    m.id.read("id");                                        // 0
                    m.agent_category.read("agent_category");                // 1
                    m.agent_device.read("agent_device");                    // 2
                    m.agent_name.read("agent_name");                        // 3
                    m.host.read("host");                                    // 4
                    m.interval_category.read("interval_category");          // 5
                    m.interval_clique.read("interval_clique");              // 6
                    m.interval_millis.read(123456);                         // 7
                    m.request_body.read("request_body");                    // 8
                    m.request_content_type.read("request_content_type");    // 9
                    m.request_headers.read("request_headers");              // 10
                    m.request_method.read("request_method");                // 11
                    m.request_params.read("request_params");                // 12
                    m.request_url.read("request_url");                      // 13
                    m.request_user_agent.read("request_user_agent");        // 14
                    m.response_body.read("response_body");                  // 15
                    m.response_code.read("response_code");                  // 16
                    m.response_content_type.read("response_content_type");  // 17
                    m.response_headers.read("response_headers");            // 18
                    m.response_time_millis.read(1234);                      // 19
                    m.size_category.read("size_category");                  // 20
                    m.size_request_bytes.read(23);                          // 21
                    m.size_response_bytes.read(45);                         // 22
                    m.write(so);
                }
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(m.id.value()).toEqual("id");                                        // 0
                    expect(m.agent_category.value()).toEqual("agent_category");                // 1
                    expect(m.agent_device.value()).toEqual("agent_device");                    // 2
                    expect(m.agent_name.value()).toEqual("agent_name");                        // 3
                    expect(m.host.value()).toEqual("host");                                    // 4
                    expect(m.interval_category.value()).toEqual("interval_category");          // 5
                    expect(m.interval_clique.value()).toEqual("interval_clique");              // 6
                    expect(m.interval_millis.value()).toEqual(123456);                         // 7
                    expect(m.request_body.value()).toEqual("request_body");                    // 8
                    expect(m.request_content_type.value()).toEqual("request_content_type");    // 9
                    expect(m.request_headers.value()).toEqual("request_headers");              // 10
                    expect(m.request_method.value()).toEqual("request_method");                // 11
                    expect(m.request_params.value()).toEqual("request_params");                // 12
                    expect(m.request_url.value()).toEqual("request_url");                      // 13
                    expect(m.request_user_agent.value()).toEqual("request_user_agent");        // 14
                    expect(m.response_body.value()).toEqual("response_body");                  // 15
                    expect(m.response_code.value()).toEqual("response_code");                  // 16
                    expect(m.response_content_type.value()).toEqual("response_content_type");  // 17
                    expect(m.response_headers.value()).toEqual("response_headers");            // 18
                    expect(m.response_time_millis.value()).toEqual(1234);                      // 19
                    expect(m.size_category.value()).toEqual("size_category");                  // 20
                    expect(m.size_request_bytes.value()).toEqual(23);                          // 21
                    expect(m.size_response_bytes.value()).toEqual(45);                         // 22
                    expect(m.length()).toEqual(282);
                }
            }
        }
    }

}
