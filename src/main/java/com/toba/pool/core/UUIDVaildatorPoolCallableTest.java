package com.toba.pool.core;

import com.toba.pool.core.utils.ScStringUtils;
import com.toba.pool.core.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class UUIDVaildatorPoolCallableTest {

	public static void main(String[] args) {
		String checkTargetId = "06754b37-56fc-47bd-897f-10ca1ad1abe1";
		int maxTotal = 3;
		String startTime = ScStringUtils.getCurrentTimeOfLog();
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(5);
		//config.setMaxWaitMillis(50);
		GenericObjectPool<UUIDUtil> uuidValidatorPool = new GenericObjectPool<UUIDUtil>(new UUIDVaildatorPool(), config);

//		for (int ii = 0; ii < 20; ii++) {
//			UUIDUtil util2 = null;
//			try {
//				util2 = uuidValidatorPool.borrowObject();
//				util2.isValidUUID("");
//				uuidValidatorPool.returnObject(util2);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		ExecutorService executor = Executors.newFixedThreadPool(8);

		List<Callable<String>> valStatusJobs = new ArrayList<>();

		String checkTargetId2 = "06754b37-56fc-47bd-897f-10ca1ad1";
		for (int ii = 0; ii < 1300; ii++) {
			String temp = StringUtils.leftPad(Integer.toString(ii), 4, "0");
			String checkTargetId3 = checkTargetId2 + temp;
			UuidValidatorCallable callable = new UuidValidatorCallable(uuidValidatorPool, checkTargetId3);
			valStatusJobs.add(callable);

		}

		List<Future<String>> resultList = null;
		try {
			resultList = executor.invokeAll(valStatusJobs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Future<String> future : resultList) {
			try {
				log.debug("return:{}",future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();

		String endTime = ScStringUtils.getCurrentTimeOfLog();
		System.out.println("-----------------------------------------------------------");

		log.info("## StartTime: "+startTime);
		log.info("##   EndTime: "+endTime);

	}



}
