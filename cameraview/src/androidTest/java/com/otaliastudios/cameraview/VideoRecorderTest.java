package com.otaliastudios.cameraview;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNull;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class VideoRecorderTest extends BaseTest {

    @Test
    public void testRecorder() {
        VideoResult result = new VideoResult();
        VideoRecorder.VideoResultListener listener = Mockito.mock(VideoRecorder.VideoResultListener.class);
        VideoRecorder recorder = new VideoRecorder(result, listener) {
            void start() {}
            void stop() {
                dispatchResult();
            }
        };
        recorder.start();
        recorder.stop();
        Mockito.verify(listener, Mockito.times(1)).onVideoResult(result);
        assertNull(recorder.mListener);
        assertNull(recorder.mResult);
    }
}
