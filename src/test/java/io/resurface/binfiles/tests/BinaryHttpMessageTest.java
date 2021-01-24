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
                    expect(m.id.toString()).toBeNull();
                    expect(m.agent_category.toString()).toBeNull();
                    expect(m.agent_device.toString()).toBeNull();
                    expect(m.agent_name.toString()).toBeNull();
                    expect(m.host.toString()).toBeNull();
                    expect(m.interval_category.toString()).toBeNull();
                    expect(m.interval_clique.toString()).toBeNull();
                    expect(m.interval_millis).toEqual(0);
                    expect(m.request_body.toString()).toBeNull();
                    expect(m.request_content_type.toString()).toBeNull();
                    expect(m.request_headers.toString()).toBeNull();
                    expect(m.request_method.toString()).toBeNull();
                    expect(m.request_params.toString()).toBeNull();
                    expect(m.request_url.toString()).toBeNull();
                    expect(m.request_user_agent.toString()).toBeNull();
                    expect(m.response_body.toString()).toBeNull();
                    expect(m.response_code.toString()).toBeNull();
                    expect(m.response_content_type.toString()).toBeNull();
                    expect(m.response_headers.toString()).toBeNull();
                    expect(m.response_time_millis).toEqual(0);
                    expect(m.size_category.toString()).toBeNull();
                    expect(m.size_request_bytes).toEqual(0);
                    expect(m.size_response_bytes).toEqual(0);
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
                    m.id.set("id");
                    m.agent_category.set("agent_category");
                    m.agent_device.set("agent_device");
                    m.agent_name.set("agent_name");
                    m.host.set("host");
                    m.interval_category.set("interval_category");
                    m.interval_clique.set("interval_clique");
                    m.interval_millis = 123456;
                    m.request_body.set("request_body");
                    m.request_content_type.set("request_content_type");
                    m.request_headers.set("request_headers");
                    m.request_method.set("request_method");
                    m.request_params.set("request_params");
                    m.request_url.set("request_url");
                    m.request_user_agent.set("request_user_agent");
                    m.response_body.set("response_body");
                    m.response_code.set("response_code");
                    m.response_content_type.set("response_content_type");
                    m.response_headers.set("response_headers");
                    m.response_time_millis = 1234;
                    m.size_category.set("size_category");
                    m.size_request_bytes = 23;
                    m.size_response_bytes = 45;
                    m.write(so);
                }
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(m.id.toString()).toEqual("id");
                    expect(m.agent_category.toString()).toEqual("agent_category");
                    expect(m.agent_device.toString()).toEqual("agent_device");
                    expect(m.agent_name.toString()).toEqual("agent_name");
                    expect(m.host.toString()).toEqual("host");
                    expect(m.interval_category.toString()).toEqual("interval_category");
                    expect(m.interval_clique.toString()).toEqual("interval_clique");
                    expect(m.interval_millis).toEqual(123456);
                    expect(m.request_body.toString()).toEqual("request_body");
                    expect(m.request_content_type.toString()).toEqual("request_content_type");
                    expect(m.request_headers.toString()).toEqual("request_headers");
                    expect(m.request_method.toString()).toEqual("request_method");
                    expect(m.request_params.toString()).toEqual("request_params");
                    expect(m.request_url.toString()).toEqual("request_url");
                    expect(m.request_user_agent.toString()).toEqual("request_user_agent");
                    expect(m.response_body.toString()).toEqual("response_body");
                    expect(m.response_code.toString()).toEqual("response_code");
                    expect(m.response_content_type.toString()).toEqual("response_content_type");
                    expect(m.response_headers.toString()).toEqual("response_headers");
                    expect(m.response_time_millis).toEqual(1234);
                    expect(m.size_category.toString()).toEqual("size_category");
                    expect(m.size_request_bytes).toEqual(23);
                    expect(m.size_response_bytes).toEqual(45);
                    expect(m.length()).toEqual(278);
                }
            }
        }
    }

}
