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
        System.out.println("Dequeue## started... dequeueing bigqueue");
        int index = 0;
        while(true) {
            if (bigQueue.size() == 0) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Dequeue## bigQueue is empty..."+bigQueue.size());
                    bigQueue.flush();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            String record = null;
            try {
                //System.out.println("Dequeue## bigqueue.size: "+bigQueue.size());
                record = new String(bigQueue.dequeue());
                index++;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ((index % 1000) == 0) {
                System.out.println("Dequeue## queueSize: " + bigQueue.size() + " = record: " + record);
            }
        }
    }
}
