package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloWorldTest {

    private Calendar calendar;
    private ResourceBundle resourceBundle;

    @Before
    public void setUp(){
        calendar = Calendar.getInstance();
        Locale.setDefault(new Locale("ru", "RU"));
        resourceBundle = ResourceBundle.getBundle("ResourceMessage", Locale.getDefault());
    }

    private String getEncodingMessage(String message){
        try {
            return new String(message.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testMorning() throws Exception {
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals(getEncodingMessage(resourceBundle.getString("morning")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.HOUR, 9);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("morning")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("morning")), HelloWorld.welcomeMessage(calendar));
    }

    @Test
    public void testDay() throws Exception {
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals(getEncodingMessage(resourceBundle.getString("day")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.AM_PM, 1);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("day")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("day")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals(getEncodingMessage(resourceBundle.getString("day")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.HOUR, 7);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("day")), HelloWorld.welcomeMessage(calendar));
    }

    @Test
    public void testEvening() throws Exception {
        calendar.set(Calendar.HOUR, 7);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals(getEncodingMessage(resourceBundle.getString("evening")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.HOUR, 11);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("evening")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.AM_PM, 0);
        Assert.assertNotEquals(getEncodingMessage(resourceBundle.getString("evening")), HelloWorld.welcomeMessage(calendar));

        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals(getEncodingMessage(resourceBundle.getString("evening")), HelloWorld.welcomeMessage(calendar));
    }

    @Test
    public void testNight() throws Exception {
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals(getEncodingMessage(resourceBundle.getString("night")), HelloWorld.welcomeMessage(calendar));
    }

    @Test
    public void testLocale() throws UnsupportedEncodingException {
        Locale.setDefault(new Locale("en", "US"));
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.AM_PM, 1);
        resourceBundle = ResourceBundle.getBundle("ResourceMessage", new Locale("en", "US"));
        Assert.assertEquals(resourceBundle.getString("evening"), HelloWorld.getMessage("evening"));
    }
}
