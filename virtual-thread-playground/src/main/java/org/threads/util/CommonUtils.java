package org.threads.util;

import java.time.Duration;

public class CommonUtils {

    public static void sleep(Duration duration){
        try{
            Thread.sleep(duration.toMillis());
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
