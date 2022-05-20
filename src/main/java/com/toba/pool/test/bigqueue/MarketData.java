package com.toba.pool.test.bigqueue;

import lombok.Getter;
import lombok.Setter;
import net.openhft.chronicle.wire.SelfDescribingMarshallable;

@Getter
@Setter
public class MarketData extends SelfDescribingMarshallable {

    int securityId;
    long time;
    float last;
    float high;
    float low;

}