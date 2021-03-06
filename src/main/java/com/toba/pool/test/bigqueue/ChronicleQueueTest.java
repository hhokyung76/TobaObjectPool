package com.toba.pool.test.bigqueue;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.wire.DocumentContext;


public class ChronicleQueueTest {
    public static void main(String[] args) {
        //writingQueue();
        readingQueue();
    }

    public static void writingQueue() {

        final MarketData marketData = new MarketData();

        final ChronicleQueue q = ChronicleQueue.single("market-data3");
        final ExcerptAppender appender = q.acquireAppender();

        for (long i = 0; i < 1000000; i++) {
            try (final DocumentContext document =
                         appender.acquireWritingDocument(false)) {
                document
                        .wire()
                        .bytes()
                        .writeObject(MarketData.class,
                                MarketDataUtil.recycle(marketData));
            }
        }
    }


    public static void readingQueue() {

        final ChronicleQueue q = ChronicleQueue.single("market-data3");
        final ExcerptTailer tailer = q.createTailer();

        for (long i = 0; i < 1000000; i++) {
            try (final DocumentContext document =
                         tailer.readingDocument()) {
                MarketData marketData = document
                        .wire()
                        .bytes()
                        .readObject(MarketData.class);

                if ((i % 1000) == 0)
                    System.out.println(i+" "+marketData);
            }
        }
        System.out.println("q.lastIndex(): "+q.lastIndex());
    }

}