// © 2016-2021 Resurface Labs Inc.

package io.resurface.binfiles.tests;

import io.resurface.binfiles.BinaryHttpMessage;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
                    expect(m.id).toBeNull();
                    expect(m.agent_category).toBeNull();
                    expect(m.agent_device).toBeNull();
                    expect(m.agent_name).toBeNull();
                    expect(m.host).toBeNull();
                    expect(m.interval_category).toBeNull();
                    expect(m.interval_clique).toBeNull();
                    expect(m.interval_millis).toEqual(0);
                    expect(m.request_body).toBeNull();
                    expect(m.request_content_type).toBeNull();
                    expect(m.request_headers).toBeNull();
                    expect(m.request_method).toBeNull();
                    expect(m.request_params).toBeNull();
                    expect(m.request_url).toBeNull();
                    expect(m.request_user_agent).toBeNull();
                    expect(m.response_body).toBeNull();
                    expect(m.response_code).toBeNull();
                    expect(m.response_content_type).toBeNull();
                    expect(m.response_headers).toBeNull();
                    expect(m.response_time_millis).toEqual(0);
                    expect(m.size_category).toBeNull();
                    expect(m.size_request).toEqual(0);
                    expect(m.size_response).toEqual(0);
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
                    m.id = "id".getBytes(StandardCharsets.UTF_8);
                    m.agent_category = "agent_category".getBytes(StandardCharsets.UTF_8);
                    m.agent_device = "agent_device".getBytes(StandardCharsets.UTF_8);
                    m.agent_name = "agent_name".getBytes(StandardCharsets.UTF_8);
                    m.host = "host".getBytes(StandardCharsets.UTF_8);
                    m.interval_category = "interval_category".getBytes(StandardCharsets.UTF_8);
                    m.interval_clique = "interval_clique".getBytes(StandardCharsets.UTF_8);
                    m.interval_millis = 123456;
                    m.request_body = "request_body".getBytes(StandardCharsets.UTF_8);
                    m.request_content_type = "request_content_type".getBytes(StandardCharsets.UTF_8);
                    m.request_headers = "request_headers".getBytes(StandardCharsets.UTF_8);
                    m.request_method = "request_method".getBytes(StandardCharsets.UTF_8);
                    m.request_params = "request_params".getBytes(StandardCharsets.UTF_8);
                    m.request_url = "request_url".getBytes(StandardCharsets.UTF_8);
                    m.request_user_agent = "request_user_agent".getBytes(StandardCharsets.UTF_8);
                    m.response_body = "response_body".getBytes(StandardCharsets.UTF_8);
                    m.response_code = "response_code".getBytes(StandardCharsets.UTF_8);
                    m.response_content_type = "response_content_type".getBytes(StandardCharsets.UTF_8);
                    m.response_headers = "response_headers".getBytes(StandardCharsets.UTF_8);
                    m.response_time_millis = 1234;
                    m.size_category = "size_category".getBytes(StandardCharsets.UTF_8);
                    m.size_request = 23;
                    m.size_response = 45;
                    m.write(so);
                }
            }
        }

        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    m.read(si);
                    expect(new String(m.id)).toEqual("id");
                    expect(new String(m.agent_category)).toEqual("agent_category");
                    expect(new String(m.agent_device)).toEqual("agent_device");
                    expect(new String(m.agent_name)).toEqual("agent_name");
                    expect(new String(m.host)).toEqual("host");
                    expect(new String(m.interval_category)).toEqual("interval_category");
                    expect(new String(m.interval_clique)).toEqual("interval_clique");
                    expect(m.interval_millis).toEqual(123456);
                    expect(new String(m.request_body)).toEqual("request_body");
                    expect(new String(m.request_content_type)).toEqual("request_content_type");
                    expect(new String(m.request_headers)).toEqual("request_headers");
                    expect(new String(m.request_method)).toEqual("request_method");
                    expect(new String(m.request_params)).toEqual("request_params");
                    expect(new String(m.request_url)).toEqual("request_url");
                    expect(new String(m.request_user_agent)).toEqual("request_user_agent");
                    expect(new String(m.response_body)).toEqual("response_body");
                    expect(new String(m.response_code)).toEqual("response_code");
                    expect(new String(m.response_content_type)).toEqual("response_content_type");
                    expect(new String(m.response_headers)).toEqual("response_headers");
                    expect(m.response_time_millis).toEqual(1234);
                    expect(new String(m.size_category)).toEqual("size_category");
                    expect(m.size_request).toEqual(23);
                    expect(m.size_response).toEqual(45);
                    expect(m.length()).toEqual(278);
                }
            }
        }
    }

    @Test
    public void performanceTest() throws Exception {
        int iterations = 100000;

        long start = System.currentTimeMillis();
        try (FileOutputStream fo = new FileOutputStream(FILE)) {
            try (BufferedOutputStream bos = new BufferedOutputStream(fo, 4096 * 64)) {
                try (ObjectOutputStream so = new ObjectOutputStream(bos)) {
                    for (int i = 0; i < iterations; i++) {
                        BinaryHttpMessage m = new BinaryHttpMessage();
                        m.id = "id".getBytes(StandardCharsets.UTF_8);
                        m.agent_category = "agent_category".getBytes(StandardCharsets.UTF_8);
                        m.agent_device = "agent_device".getBytes(StandardCharsets.UTF_8);
                        m.agent_name = "agent_name".getBytes(StandardCharsets.UTF_8);
                        m.host = "host".getBytes(StandardCharsets.UTF_8);
                        m.interval_category = "interval_category".getBytes(StandardCharsets.UTF_8);
                        m.interval_clique = "interval_clique".getBytes(StandardCharsets.UTF_8);
                        m.interval_millis = 123456;
                        m.request_body = "request_body".getBytes(StandardCharsets.UTF_8);
                        m.request_content_type = "request_content_type".getBytes(StandardCharsets.UTF_8);
                        m.request_headers = "request_headers".getBytes(StandardCharsets.UTF_8);
                        m.request_method = "request_method".getBytes(StandardCharsets.UTF_8);
                        m.request_params = "request_params".getBytes(StandardCharsets.UTF_8);
                        m.request_url = "request_url".getBytes(StandardCharsets.UTF_8);
                        m.request_user_agent = "request_user_agent".getBytes(StandardCharsets.UTF_8);
                        m.response_body = "response_body".getBytes(StandardCharsets.UTF_8);
                        m.response_code = "response_code".getBytes(StandardCharsets.UTF_8);
                        m.response_content_type = "response_content_type".getBytes(StandardCharsets.UTF_8);
                        m.response_headers = "response_headers".getBytes(StandardCharsets.UTF_8);
                        m.response_time_millis = 1234;
                        m.size_category = "size_category".getBytes(StandardCharsets.UTF_8);
                        m.size_request = 23;
                        m.size_response = 45;
                        m.write(so);
                    }
                }
            }
        }
        System.out.println("Written in " + (System.currentTimeMillis() - start) + " millis");

        start = System.currentTimeMillis();
        try (FileInputStream fi = new FileInputStream(FILE)) {
            try (BufferedInputStream bis = new BufferedInputStream(fi, 4096 * 64)) {
                try (ObjectInputStream si = new ObjectInputStream(bis)) {
                    int count = 0;
                    BinaryHttpMessage m = new BinaryHttpMessage();
                    for (int i = 0; i < iterations; i++) {
                        m.read(si);
                        count++;
                    }
                    expect(count).toEqual(iterations);
                }
            }
        }
        System.out.println("Read in " + (System.currentTimeMillis() - start) + " millis");
    }

}
