package com.toba.pool.test.bigqueue;

import com.leansoft.bigqueue.IBigQueue;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Callable;

@Slf4j
public class BigDeQueueingCallable implements Callable<Integer> {

    private IBigQueue bigQueue;

    public BigDeQueueingCallable(IBigQueue sbigQueue) {
        bigQueue = sbigQueue;
    }


    @Override
    public Integer call() throws Exception {
        System.out.println("Dequeue## started... dequeueing bigqueue size: "+bigQueue.size());
        int index = 0;
        long totalTargetCount = bigQueue.size();
        for (int ii = 0; ii < totalTargetCount; ii++) {
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
        bigQueue.close();
        return Integer.valueOf(index);
    }
}
