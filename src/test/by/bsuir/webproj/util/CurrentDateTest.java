package test.by.bsuir.webproj.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import by.bsuir.webproj.util.CurrentDate;
import org.junit.Test;

import java.sql.Date;

/**
 * Created by Brotorias on 19.06.2016.
 */
public class CurrentDateTest {
    @Test
    public void goodResultTest(){
        final Date TEST_DATA = Date.valueOf("2016-10-10");
        final String RESULT = "2016-10-10";

        assertEquals(RESULT, CurrentDate.getStringDate(TEST_DATA));
    }

    @Test
    public void badResultTest(){
        final Date TEST_DATA = null;
        assertTrue(CurrentDate.getStringDate(TEST_DATA).isEmpty());
    }
}
