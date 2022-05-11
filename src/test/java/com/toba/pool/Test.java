package com.toba.pool;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
        String checkTargetId2 = "TEMP=";

        for (int ii = 0; ii < 1000; ii++) {
            String temp = StringUtils.leftPad(Integer.toString(ii), 4, "0");
            System.out.println("temp: "+temp);
        }
    }
}
