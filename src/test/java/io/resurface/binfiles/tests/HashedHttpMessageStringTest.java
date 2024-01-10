// © 2016-2024 Graylog, Inc.

package io.resurface.binfiles.tests;

import io.resurface.binfiles.HashedDictionary;
import io.resurface.binfiles.HashedHttpMessageString;
import org.junit.Test;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

/**
 * Tests against string field used in compressed message format, backed by hashing dictionary.
 */
public class HashedHttpMessageStringTest {

    @Test
    public void readAndWriteTest() throws Exception {
        HashedDictionary dictionary = new HashedDictionary();
        dictionary.put(-1, "foo");
        dictionary.put(-2, "bar");
        dictionary.put(-3, "bleep");
        dictionary.put(-4, "blorp");

        HashedHttpMessageString string = new HashedHttpMessageString(dictionary);
        expect(string.bytes()).toEqual(4);
        expect(string.isNull()).toBeTrue();
        expect(string.length()).toEqual(0);
        expect(string.value()).toBeNull();

        string.read("pear");
        expect(string.bytes()).toEqual(8);
        expect(string.isNull()).toBeFalse();
        expect(string.length()).toEqual(4);
        expect(string.value()).toEqual("pear");

        string.read("bleep");
        expect(string.bytes()).toEqual(4);
        expect(string.isNull()).toBeFalse();
        expect(string.length()).toEqual(0);
        expect(string.value()).toEqual("bleep");
    }

}
