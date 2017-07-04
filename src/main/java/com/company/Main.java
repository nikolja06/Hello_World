package com.company;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws UnsupportedEncodingException {
        log.info("Start program");

        Calendar calendar = Calendar.getInstance();
        System.out.println(HelloWorld.welcomeMessage(calendar));

        log.info("Finish program\n");
    }
}
