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
        resourceBundle = ResourceBundle.getBundle("ResourceMessage");
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
    public void test_getKey_Bottom_Line_Morning() throws Exception {
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("morning", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Middle_Line_Morning() throws Exception {
        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("morning", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Top_Line_Morning() throws Exception {
        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("morning", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Bottom_Line_Day() throws Exception {
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("day", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Middle_Line_Day_PM() throws Exception {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("day", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Middle_Line_Day_AM() throws Exception {
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("day", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Middle_Line_Day_Negative() throws Exception {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertNotEquals("day", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Top_Line_Day() throws Exception {
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("day", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Bottom_Line_Evening() throws Exception {
        calendar.set(Calendar.HOUR, 7);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("evening", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Middle_Line_Evening() throws Exception {
        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("evening", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Top_Line_Evening() throws Exception {
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("evening", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Bottom_Line_Night() throws Exception {
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("night", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Middle_Line_Night() throws Exception {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("night", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getKey_Top_Line_Night() throws Exception {
        calendar.set(Calendar.HOUR, 5);
        calendar.set(Calendar.AM_PM, 0);
        Assert.assertEquals("night", HelloWorld.getKey(calendar));
    }

    @Test
    public void test_getMessage_Morning(){
        Locale.setDefault(Locale.US);
        resourceBundle = ResourceBundle.getBundle("ResourceMessage");
        Assert.assertEquals("Good morning, World!!!", resourceBundle.getString("morning"));
    }

    @Test
    public void test_getMessage_Day(){
        Locale.setDefault(Locale.US);
        resourceBundle = ResourceBundle.getBundle("ResourceMessage");
        Assert.assertEquals("Good day, World!!!", resourceBundle.getString("day"));
    }

    @Test
    public void test_getMessage_Evening(){
        Locale.setDefault(Locale.US);
        resourceBundle = ResourceBundle.getBundle("ResourceMessage");
        Assert.assertEquals("Good evening, World!!!", resourceBundle.getString("evening"));
    }

    @Test
    public void test_getMessage_Night(){
        Locale.setDefault(Locale.US);
        resourceBundle = ResourceBundle.getBundle("ResourceMessage");
        Assert.assertEquals("Good night, World!!!", resourceBundle.getString("night"));
    }

    @Test
    public void testLocale() throws UnsupportedEncodingException {
        Locale.setDefault(new Locale("en", "US"));
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.AM_PM, 1);
        resourceBundle = ResourceBundle.getBundle("ResourceMessage", new Locale("en", "US"));
        Assert.assertEquals(resourceBundle.getString("evening"), HelloWorld.getMessage("evening"));
    }

    @Test
    public void testNotExistLocale(){
        Locale.setDefault(new Locale("en","GB"));
        resourceBundle = ResourceBundle.getBundle("ResourceMessage");
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.AM_PM, 1);
        Assert.assertEquals("Good evening, World!!!", resourceBundle.getString("evening"));
    }
}
