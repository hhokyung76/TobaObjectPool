package com.toba.pool.test.bigqueue;

import com.leansoft.bigqueue.BigQueueImpl;
import com.leansoft.bigqueue.IBigQueue;
import com.toba.pool.core.utils.ScStringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class BigQueueTest {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        for (int ii = 0; ii < 100000; ii++) {
            String queueDir = "D:\\tmp\\popout2"; //System.getProperty("user.home");
            String queueName = "baeldung-queue";
            IBigQueue bigQueue = new BigQueueImpl(queueDir, queueName);

            BigDeQueueingCallable deQueueing = new BigDeQueueingCallable(bigQueue);


            Future<Integer> stringFuture = executor.submit(deQueueing);

            System.out.println("Started!");

            System.out.println(stringFuture.get()); //블록킹 콜, 작업이 완료될 때 까지 기다린다.

            System.out.println("callable count: "+ii+" time: "+ ScStringUtils.getCurrentTime());
            Thread.sleep(6000);

        }
    }

    public static void main4(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(8);

//        String queueDir = System.getProperty("user.home");
        String queueDir = "D:\\tmp\\popout2"; //System.getProperty("user.home");
        String queueName = "baeldung-queue";
        IBigQueue bigQueue = new BigQueueImpl(queueDir, queueName);

        BigDeQueueingRunnable deQueueing = new BigDeQueueingRunnable(bigQueue);
        executor.submit(deQueueing);
//        BigDeQueueingRunnable deQueueing2 = new BigDeQueueingRunnable(bigQueue);
//        executor.submit(deQueueing2);

        //insertQueueForThread(bigQueue);
        //insertQueue();
        //readQueue();
    }


    public static void main3(String[] args) throws IOException {

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

        String queueDir = "D:\\tmp\\popout2"; //System.getProperty("user.home");
        String queueName = "baeldung-queue";
        IBigQueue bigQueue = new BigQueueImpl(queueDir, queueName);

        for (int i = 1; i <= 100000; i++) {
            if ((i%1000) == 0) {
                System.out.println("I'm inserting data....."+i);
            }
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
