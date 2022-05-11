package com.toba.pool.core;

import ua_parser.Parser;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.toba.pool.core.utils.ScStringUtils;

import ua_parser.Client;

public class UserAgentParserPool extends BasePooledObjectFactory<Parser> {

	@Override
	public Parser create() throws Exception {
		// TODO Auto-generated method stub
		return new Parser();
	}

	@Override
	public PooledObject<Parser> wrap(Parser parser) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<Parser>(parser);
	}

}
