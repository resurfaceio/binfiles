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

    public final String FILE = "./target/http-messages.bin";

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
                    expect(m.id.value()).toBeNull();
                    expect(m.agent_category.value()).toBeNull();
                    expect(m.agent_device.value()).toBeNull();
                    expect(m.agent_name.value()).toBeNull();
                    expect(m.host.value()).toBeNull();
                    expect(m.interval_category.value()).toBeNull();
                    expect(m.interval_clique.value()).toBeNull();
                    expect(m.interval_millis.value()).toEqual(0);
                    expect(m.request_body.value()).toBeNull();
                    expect(m.request_content_type.value()).toBeNull();
                    expect(m.request_headers.value()).toBeNull();
                    expect(m.request_method.value()).toBeNull();
                    expect(m.request_params.value()).toBeNull();
                    expect(m.request_url.value()).toBeNull();
                    expect(m.request_user_agent.value()).toBeNull();
                    expect(m.response_body.value()).toBeNull();
                    expect(m.response_code.value()).toBeNull();
                    expect(m.response_content_type.value()).toBeNull();
                    expect(m.response_headers.value()).toBeNull();
                    expect(m.response_time_millis.value()).toEqual(0);
                    expect(m.size_category.value()).toBeNull();
                    expect(m.size_request_bytes.value()).toEqual(0);
                    expect(m.size_response_bytes.value()).toEqual(0);
                    expect(m.length()).toEqual(24);
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
                    m.id.read("id");
                    m.agent_category.read("agent_category");
                    m.agent_device.read("agent_device");
                    m.agent_name.read("agent_name");
                    m.host.read("host");
                    m.interval_category.read("interval_category");
                    m.interval_clique.read("interval_clique");
                    m.interval_millis.read(123456);
                    m.request_body.read("request_body");
                    m.request_content_type.read("request_content_type");
                    m.request_headers.read("request_headers");
                    m.request_method.read("request_method");
                    m.request_params.read("request_params");
                    m.request_url.read("request_url");
                    m.request_user_agent.read("request_user_agent");
                    m.response_body.read("response_body");
                    m.response_code.read("response_code");
                    m.response_content_type.read("response_content_type");
                    m.response_headers.read("response_headers");
                    m.response_time_millis.read(1234);
                    m.size_category.read("size_category");
                    m.size_request_bytes.read(23);
                    m.size_response_bytes.read(45);
                    m.write(so);
                }
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(m.id.value()).toEqual("id");
                    expect(m.agent_category.value()).toEqual("agent_category");
                    expect(m.agent_device.value()).toEqual("agent_device");
                    expect(m.agent_name.value()).toEqual("agent_name");
                    expect(m.host.value()).toEqual("host");
                    expect(m.interval_category.value()).toEqual("interval_category");
                    expect(m.interval_clique.value()).toEqual("interval_clique");
                    expect(m.interval_millis.value()).toEqual(123456);
                    expect(m.request_body.value()).toEqual("request_body");
                    expect(m.request_content_type.value()).toEqual("request_content_type");
                    expect(m.request_headers.value()).toEqual("request_headers");
                    expect(m.request_method.value()).toEqual("request_method");
                    expect(m.request_params.value()).toEqual("request_params");
                    expect(m.request_url.value()).toEqual("request_url");
                    expect(m.request_user_agent.value()).toEqual("request_user_agent");
                    expect(m.response_body.value()).toEqual("response_body");
                    expect(m.response_code.value()).toEqual("response_code");
                    expect(m.response_content_type.value()).toEqual("response_content_type");
                    expect(m.response_headers.value()).toEqual("response_headers");
                    expect(m.response_time_millis.value()).toEqual(1234);
                    expect(m.size_category.value()).toEqual("size_category");
                    expect(m.size_request_bytes.value()).toEqual(23);
                    expect(m.size_response_bytes.value()).toEqual(45);
                    expect(m.length()).toEqual(278);
                }
            }
        }
    }

}
