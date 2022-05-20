package com.toba.pool.test.bigqueue;

import org.infobip.lib.popout.Deserializer;
import org.infobip.lib.popout.FileQueue;
import org.infobip.lib.popout.Serializer;
import org.infobip.lib.popout.WalFilesConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PopOutTest {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = FileQueue.<String>synced()
                .name("synced-queue-add2")
                .folder("/Users/hklee/popout")
                .serializer(Serializer.STRING)
                .deserializer(Deserializer.STRING)
                .wal(WalFilesConfig.builder()
                        .maxCount(1000)
                        .build())
                .build();

        List<String> tempList = new ArrayList<String>();
        for (int ii = 0; ii < 10000; ii++) {
            tempList.add(Integer.toString(ii));
        }

        for (String character : tempList) {
            queue.add(character);
        }

        boolean isOn = true;
        while(isOn) {
            String temp = queue.poll();
            System.out.println("polled str: "+temp);
            System.out.println("ater queue size: "+queue.size());
           // Thread.sleep(100);

            if (queue.size() == 0) {
                isOn = false;
            }
        }

        queue.clear();

    }
}
