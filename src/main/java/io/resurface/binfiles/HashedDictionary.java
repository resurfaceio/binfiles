// © 2016-2023 Graylog, Inc.

package io.resurface.binfiles;

import io.airlift.slice.Slice;

import java.util.HashMap;
import java.util.Map;

import static io.airlift.slice.Slices.utf8Slice;

/**
 * Dictionary for persistent string hashing.
 */
public final class HashedDictionary {

    /**
     * Add entry to hashing dictionary.
     */
    public void put(int hash, String s) {
        if (hash >= 0) throw new IllegalArgumentException("Hashing keys must be less than zero");
        if (slices.containsKey(hash)) throw new IllegalArgumentException("Duplicate hashing key: " + hash);
        if (values.containsKey(s)) throw new IllegalArgumentException("Duplicate hashing value: " + s);
        slices.put(hash, utf8Slice(s));
        values.put(s, hash);
    }

    public final Map<Integer, Slice> slices = new HashMap<>();
    public final Map<String, Integer> values = new HashMap<>();

}
