package com.toba.pool.core;

import com.toba.pool.core.utils.ScStringUtils;
import com.toba.pool.core.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.util.StopWatch;

import java.time.LocalTime;
import java.util.concurrent.Callable;

@Slf4j
public class UuidValidatorCallable implements Callable<String> {

    private GenericObjectPool<UUIDUtil> uuidValidatorPool;
    private String checkTargetStr;

    public UuidValidatorCallable(GenericObjectPool<UUIDUtil> sUuidValidatorPool, String scheckTargetStr) {
        uuidValidatorPool = sUuidValidatorPool;
        this.checkTargetStr = scheckTargetStr;
    }

    @Override
    public String call() throws Exception {
        String result = Thread.currentThread().getName()+"=>Called at " + ScStringUtils.getCurrentTimeOfLog();

        //String checkTargetId = "06754b37-56fc-47bd-897f-10ca1ad1abe1";

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int ii = 0; ii < 10; ii++) {
            UUIDUtil util = null;
            try {

                String startTime = ScStringUtils.getCurrentTimeOfLog();
                util = uuidValidatorPool.borrowObject();
                task2(util, checkTargetStr);
                uuidValidatorPool.returnObject(util);

                String endTime = ScStringUtils.getCurrentTimeOfLog();

//                log.info("startTime: " + startTime);
//                log.info("endTime: " + endTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        result += " "+ScStringUtils.getCurrentTimeOfLog()+" "+stopWatch.getTotalTimeMillis()+" "+checkTargetStr;
        return result;
    }

    public static void task2(UUIDUtil uuidUtil, String uuid) {
        boolean checkedUuid = uuidUtil.isValidUUID(uuid);
        log.info("uuid: " + uuid+" // checkedUuid: "+checkedUuid);
    }
}
