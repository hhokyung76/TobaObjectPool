package com.toba.pool.test.popout;

import com.leansoft.bigqueue.BigQueueImpl;
import com.leansoft.bigqueue.IBigQueue;

import java.io.IOException;

public class BigQueueTest {
    public static void main(String[] args) {
        try {
            insertQueue();
            readQueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        while(bigQueue.size() > 0) {
            String record = new String(bigQueue.dequeue());
            System.out.println("queueSize: "+bigQueue.size()+" = record: "+record);
        }
    }
}
