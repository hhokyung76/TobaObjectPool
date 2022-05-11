package com.toba.pool.core;

import com.toba.pool.core.utils.UUIDUtil;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import ua_parser.Parser;

public class UUIDVaildatorPool extends BasePooledObjectFactory<UUIDUtil> {

	@Override
	public UUIDUtil create() throws Exception {
		// TODO Auto-generated method stub
		return new UUIDUtil();
	}

	@Override
	public PooledObject<UUIDUtil> wrap(UUIDUtil uuidUtil) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<UUIDUtil>(uuidUtil);
	}

}
