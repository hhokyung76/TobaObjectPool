package com.toba.pool.test.bigqueue;

import java.util.concurrent.ThreadLocalRandom;

public class MarketDataUtil {

    static MarketData create() {
        MarketData marketData = new MarketData();
        int id = ThreadLocalRandom.current().nextInt(1000);
        marketData.setSecurityId(id);

        float nextFloat = ThreadLocalRandom.current().nextFloat();
        float last = 20 + 100 * nextFloat;

        marketData.setLast(last);
        marketData.setHigh(last * 1.1f);
        marketData.setLow(last * 0.9f);
        marketData.setTime(System.currentTimeMillis());

        return marketData;

    }
    static MarketData recycle(MarketData marketData) {

        final int id = ThreadLocalRandom.current().nextInt(1000);

        marketData.setSecurityId(id);

        final float nextFloat = ThreadLocalRandom.current().nextFloat();
        final float last = 20 + 100 * nextFloat;

        marketData.setLast(last);
        marketData.setHigh(last * 1.1f);
        marketData.setLow(last * 0.9f);
        marketData.setTime(System.currentTimeMillis());

        return marketData;
    }
}
