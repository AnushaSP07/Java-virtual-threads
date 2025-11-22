package org.threads.sec02;

import org.threads.util.CommonUtils;

import java.time.Duration;

public class StackTraceDemo {

    public static void main(String[] args) {
       // demo(Thread.ofPlatform());
        demo(Thread.ofVirtual().name("virtual-thread-",0));
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static void demo(Thread.Builder builder){
        for(int i = 1; i <= 20; i++){
            int j = i;
            builder.start(() -> Task.execute(j));
        }
    }
}
