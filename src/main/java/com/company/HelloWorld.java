package com.company;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloWorld {

    private static final Logger log = Logger.getLogger(HelloWorld.class.getName());

    public static String welcomeMessage(Calendar calendar) throws UnsupportedEncodingException {
        log.info("Start getting welcome message");
        String message = getMessage(getKey(calendar));
        log.info("Success");

        return message;
    }

    public static String getKey(Calendar calendar){
        log.info("Start parse key depends on time:\n input data (time): " + calendar.getTime());
        String key;
        int HOUR = calendar.get(Calendar.HOUR);
        int AM_PM = calendar.get(Calendar.AM_PM);

        if (HOUR >= 6 && HOUR < 9 && AM_PM == 0){
            key = "morning";
        }
        else if ((HOUR >= 9 && AM_PM == 0)
                || (HOUR >= 0 && HOUR < 7 && AM_PM == 1)){
            key = "day";
        }
        else if (HOUR >= 7 && HOUR < 11 && AM_PM == 1){
            key = "evening";
        }
        else{
            key = "night";
        }
        log.info("Result key: " + key);
        return key;
    }

    public static String getMessage(String key) {
        log.info("Start parse message by key = " + key);
        String result = null;
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceMessage", Locale.getDefault());
            String message = resourceBundle.getString(key);
            result = new String(message.getBytes("ISO-8859-1"), "UTF-8");
            log.info("Success!");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result;
    }
}
