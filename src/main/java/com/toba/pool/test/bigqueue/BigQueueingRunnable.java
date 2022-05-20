package com.toba.pool.test.bigqueue;

import com.leansoft.bigqueue.IBigQueue;
import com.toba.pool.core.utils.ScStringUtils;
import com.toba.pool.core.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.util.StopWatch;

import java.util.concurrent.Callable;

@Slf4j
public class BigQueueingRunnable implements Runnable {

    private IBigQueue bigQueue;

    public BigQueueingRunnable(IBigQueue sbigQueue) {
        bigQueue = sbigQueue;
    }

    @Override
    public void run() {

    }
}
