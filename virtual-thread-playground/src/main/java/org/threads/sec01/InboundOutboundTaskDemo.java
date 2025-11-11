package org.threads.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 10;

    public static void main(String[] args) {
        platformThreadDemo1();
    }

    private static void platformThreadDemo1(){
        for(int i =0 ; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = new Thread( () -> Task.ioIntensive(j) );
            thread.start();
        }
    }

    //another way to create thread
    private static void platformThreadDemo2(){
        for(int i =0 ; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = Thread.ofPlatform().unstarted(() -> Task.ioIntensive(j) );
            thread.start();
        }
    }

    // providing name and priority
    private static void platformThreadDemo3(){
        var builder = Thread.ofPlatform().name("asp", 1);
        for(int i =0 ; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    // daemon thread - threads that do not prevent the JVM from exiting
    // the thread will not keep the application running if all user threads have finished
    // the threads which are running in background
    private static void platformThreadDemo4(){
        var builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for(int i =0 ; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    // adding latch to wait for all threads to complete
    private static void platformThreadDemo5() throws InterruptedException {
        var latch = new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for(int i =0 ; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }

    // create virtual threads using builder
    // virtual threads are daemon by default
    // we cannot create non-daemon virtual threads
    // virtual threads do not have any name default
    private static void virtualThreadDemo(){
        var builder = Thread.ofVirtual();
        for(int i =0 ; i < MAX_VIRTUAL; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void virtualThreadDemo2() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofPlatform().daemon().name("virtual-", 1);
        for(int i =0 ; i < MAX_VIRTUAL; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
