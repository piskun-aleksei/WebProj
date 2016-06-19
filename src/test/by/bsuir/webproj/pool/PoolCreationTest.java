package test.by.bsuir.webproj.pool;

import by.bsuir.webproj.pool.ConnectionPool;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brotorias on 19.06.2016.
 */
public class PoolCreationTest {
    @Test
    public void goodResultTest(){
        final int RESULT = 20;
        assertEquals(RESULT, ConnectionPool.getInstance().getSize());
    }
}
