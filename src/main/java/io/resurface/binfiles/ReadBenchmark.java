// © 2016-2022 Resurface Labs Inc.

package io.resurface.binfiles;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;

/**
 * Transforms messages stored in compressed NDJSON files.
 */
public class ReadBenchmark {

    /**
     * Runs transformer as command-line program.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("\n>>> Benchmark starting");
        for (int i = 0; i < 10; i++) new ReadBenchmark();
        System.out.println(">>> Benchmark finished!\n");
    }

    /**
     * Parses every message and writes a transformed version.
     */
    public ReadBenchmark() throws Exception {
        // read configuration
        String file_in = System.getProperty("FILE_IN");
        if (file_in == null) throw new IllegalArgumentException("Missing FILE_IN");
        System.out.println("FILE_IN=" + file_in);

        // read entire file
        FileInputStream fis = new FileInputStream(file_in);
        BufferedInputStream bis = new BufferedInputStream(fis, 1000000);
        BinaryHttpMessage message = new BinaryHttpMessage();
        while (true) {
            try {
                message.read(bis);
                messages_read++;
            } catch (EOFException eof) {
                status();
                return;
            }
        }
    }

    /**
     * Print status summary.
     */
    private void status() {
        long elapsed = System.currentTimeMillis() - started;
        long rate = (messages_read * 1000 / elapsed);
        System.out.println("Messages: " + messages_read + ", Elapsed time: " + elapsed
                + " ms, Rate: " + rate + " msg/sec");
    }

    private long messages_read = 0;
    private final long started = System.currentTimeMillis();

}