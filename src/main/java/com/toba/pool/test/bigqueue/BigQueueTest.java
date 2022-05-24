package com.toba.pool.test.bigqueue;

import com.leansoft.bigqueue.BigQueueImpl;
import com.leansoft.bigqueue.IBigQueue;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BigQueueTest {
    public static void main2(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        String queueDir = "/Users/hklee/popout"; //System.getProperty("user.home");
        String queueName = "baeldung-queue";
        IBigQueue bigQueue = new BigQueueImpl(queueDir, queueName);

        BigDeQueueingRunnable deQueueing = new BigDeQueueingRunnable(bigQueue);
        executor.submit(deQueueing);
//        BigDeQueueingRunnable deQueueing2 = new BigDeQueueingRunnable(bigQueue);
//        executor.submit(deQueueing2);

        try {
            //insertQueueForThread(bigQueue);
            //insertQueue();
            readQueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        try {
            //insertQueueForThread(bigQueue);
            insertQueue();
            readQueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void insertQueueForThread(IBigQueue bigQueue) throws IOException {

        for (int i = 1; i <= 100000; i++) {
            bigQueue.enqueue(String.valueOf(i+"dlkdakldafskldsakljdsfl;kdjsmkl;sdlk;sffldk;jzdfslk;qdfsjkldfsjkldfsdsfadfsfdsdfasdfsdfsadfsadfsadfsadfsasdfadfsdsfdfsdfsdfsdfsdfsdsfdsfdfsdfsdfsadfsadsfadsfadfsdsfsdfdfsdsfdfdf").getBytes());
        }
        System.out.println("bigQueue.size: "+bigQueue.size());
    }

    public static void insertQueue() throws IOException {

        String queueDir = System.getProperty("user.home");
        String queueName = "baeldung-queue";
        IBigQueue bigQueue = new BigQueueImpl(queueDir, queueName);

        for (int i = 1; i <= 100000; i++) {
            bigQueue.enqueue(String.valueOf(i+"dlkdakldafskldsakljdsfl;kdjsmkl;sdlk;sffldk;jzdfslk;qdfsjkldfsjkldfsdsfadfsfdsdfasdfsdfsadfsadfsadfsadfsasdfadfsdsfdfsdfsdfsdfsdfsdsfdsfdfsdfsdfsadfsadsfadsfadfsdsfsdfdfsdsfdfdf").getBytes());
        }
        System.out.println("bigQueue.size: "+bigQueue.size());
    }

    public static void readQueue() throws IOException {
        String queueDir = System.getProperty("user.home");
        String queueName = "baeldung-queue";
        IBigQueue bigQueue = new BigQueueImpl(queueDir, queueName);

        System.out.println("bigQueue.size: "+bigQueue.size());

//        long index = 0;
        while(bigQueue.size() > 0) {
            String record = new String(bigQueue.dequeue());

            if ((bigQueue.size() % 1000) == 0)
                System.out.println("queueSize: "+bigQueue.size()+" = record: "+record);
        }
    }
}
