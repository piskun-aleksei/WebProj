package test.by.bsuir.webproj.handlers;

import by.bsuir.webproj.handler.CryptingHashHandler;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brotorias on 19.06.2016.
 */
public class HashTest {
    @Test
    public void goodResultTest(){
        final String RESULT = "d8578edf8458ce06fbc5bb76a58c5ca4";
        final String TEST_STRING = "qwerty";
        assertEquals(RESULT, CryptingHashHandler.getHash(TEST_STRING));
    }
    @Test
    public void badResultTest(){
        final String TEST_STRING = null;
        assertTrue(CryptingHashHandler.getHash(TEST_STRING).isEmpty());
    }

}
