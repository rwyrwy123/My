package com.example.test.mynewapptwo;

import com.example.test.mynewapptwo.utils.TimeUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void time(){
        String s = TimeUtils.getFriendlyTimeSpanByNow("2017-11-15 00:00"+":00");
        assertEquals("刚刚",s);
    }

}