package com.otaliastudios.cameraview;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class WorkerHandlerTest extends BaseTest {

    @Test
    public void testCache() {
        WorkerHandler w1 = WorkerHandler.get("handler1");
        WorkerHandler w1a = WorkerHandler.get("handler1");
        WorkerHandler w2 = WorkerHandler.get("handler2");
        assertSame(w1, w1a);
        assertNotSame(w1, w2);
    }

    @Test
    public void testStaticRun() {
        final Task<Boolean> task = new Task<>(true);
        Runnable action = new Runnable() {
            @Override
            public void run() {
                task.end(true);
            }
        };
        WorkerHandler.run(action);
        Boolean result = task.await(500);
        assertNotNull(result);
        assertTrue(result);
    }
}
