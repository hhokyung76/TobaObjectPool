package com.toba.pool.test.bigqueue;

import com.leansoft.bigqueue.IBigQueue;
import com.toba.pool.core.utils.ScStringUtils;
import com.toba.pool.core.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.concurrent.Callable;

@Slf4j
public class BigDeQueueingRunnable implements Runnable {

    private IBigQueue bigQueue;

    public BigDeQueueingRunnable(IBigQueue sbigQueue) {
        bigQueue = sbigQueue;
    }

    @Override
    public void run() {
        log.info("started... dequeueing bigqueue");
        while(true) {
            String record = null;
            try {
                log.info("## bigqueue.size: "+bigQueue.size());
                record = new String(bigQueue.dequeue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("queueSize: "+bigQueue.size()+" = record: "+record);
        }
    }
}
