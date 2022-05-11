package com.toba.pool.core;

import com.toba.pool.core.utils.ScStringUtils;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import ua_parser.Client;
import ua_parser.Parser;

public class UserAgentParserPoolTest {


	public static void main(String[] args) {
//		task();
//		task();
		System.out.println("-------------------------------------------------");
		Parser uaParser = new Parser();
		task2(uaParser);
		task2(uaParser);
		System.out.println("-------------------------------------------------");

		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(6);
		config.setMaxWaitMillis(200);

		GenericObjectPool<Parser> objectPool = new GenericObjectPool<Parser>(new UserAgentParserPool(), config);

		for (int i = 0; i < 10; i++) {
			Parser parser = null;
			try {
				parser = objectPool.borrowObject();
				task2(parser);
				objectPool.returnObject(parser);
			} catch (Exception e) {

				e.printStackTrace();

			}

		}
	}

	public static void task() {
		//String uaString = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";
		String uaString = "Delfino-StarBank/G6.1.99|Android 12|SM-G977N|F8C56D59F7A1F96B|06754b37-56fc-47bd-897f-10ca1ad1abe1";
		String startTime = ScStringUtils.getCurrentTimeOfLog();
		Parser uaParser = new Parser();
		Client c = uaParser.parse(uaString);

		String endTime = ScStringUtils.getCurrentTimeOfLog();

		System.out.println(c.userAgent.family); // => "Mobile Safari"
		System.out.println(c.userAgent.major); // => "5"
		System.out.println(c.userAgent.minor); // => "1"

		System.out.println(c.os.family); // => "iOS"
		System.out.println(c.os.major); // => "5"
		System.out.println(c.os.minor); // => "1"

		System.out.println(c.device.family); // => "iPhone"

		System.out.println("startTime: " + startTime);
		System.out.println("endTime: " + endTime);
	}

	public static void task2(Parser uaParser) {
//		String uaString = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3|00000000-0000-0000-0000-000000000000";

		String uaString = "Delfino-StarBank/G6.1.99|Android 12|SM-G977N|F8C56D59F7A1F96B|06754b37-56fc-47bd-897f-10ca1ad1abe1";

		String startTime = ScStringUtils.getCurrentTimeOfLog();
		Client c = uaParser.parse(uaString);

		String endTime = ScStringUtils.getCurrentTimeOfLog();

		System.out.println("c.userAgent.family: "+c.userAgent.family); // => "Mobile Safari"
//		System.out.println(c.userAgent.major); // => "5"
//		System.out.println(c.userAgent.minor); // => "1"

		System.out.println("c.os.family: "+c.os.family); // => "iOS"
		System.out.println(c.os.major); // => "5"
		System.out.println(c.os.minor); // => "1"

		System.out.println("c.device.family: "+c.device.family); // => "iPhone"

		System.out.println("startTime: " + startTime);
		System.out.println("endTime: " + endTime);
	}

}
