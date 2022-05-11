package com.toba.pool.core;

import com.toba.pool.core.utils.ScStringUtils;
import com.toba.pool.core.utils.UUIDUtil;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import ua_parser.Parser;

public class UUIDVaildatorPoolTest  {

	public static void main(String[] args) {
		String checkTargetId = "06754b37-56fc-47bd-897f-10ca1ad1abe1";


		for (int ii = 0; ii < 4; ii++) {
			UUIDUtil uuidUtil = new UUIDUtil();

			String startTime = ScStringUtils.getCurrentTimeOfLog();
			// uuid 포맷 체크
			boolean checkedUuid = uuidUtil.isValidUUID(checkTargetId);
			System.out.println("checkTargetId: " + checkTargetId + " checkedUuid: " + checkedUuid);
			if (checkedUuid) {
				String madid = checkTargetId;
			}
			String endTime = ScStringUtils.getCurrentTimeOfLog();

			System.out.println("startTime: " + startTime);
			System.out.println("endTime: " + endTime);
		}

		System.out.println("-----------------------------------------------------------");
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(6);
		config.setMaxWaitMillis(20);

		GenericObjectPool<UUIDUtil> objectPool = new GenericObjectPool<UUIDUtil>(new UUIDVaildatorPool(), config);

		for (int ii = 0; ii < 10; ii++) {
			UUIDUtil util = null;
			try {

				String startTime = ScStringUtils.getCurrentTimeOfLog();
				util = objectPool.borrowObject();
				task2(util, checkTargetId);
				objectPool.returnObject(util);

				String endTime = ScStringUtils.getCurrentTimeOfLog();

				System.out.println("startTime: " + startTime);
				System.out.println("endTime: " + endTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void task2(UUIDUtil uuidUtil, String uuid) {
		boolean checkedUuid = uuidUtil.isValidUUID(uuid);
		System.out.println("uuid: " + uuid+" // checkedUuid: "+checkedUuid);
	}

}
