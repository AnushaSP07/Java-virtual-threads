1. types of creating theads
2. daemon threads 
  - threads that run in the background and do not prevent the JVM from exiting when the program finishes
  - even the main method/thread finishes the daemon threads will continue to run until the JVM exits

3. countDownLatch
  - a synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes
  - initialized with a count, and threads can call await() to wait until the count reaches zero
  - other threads can call countDown() to decrement the count, and when it reaches zero, all waiting threads are released
  - once it reaches the count of zero, it will exit and cannot be reused

Virtual Threads

- Java has introduced a new type of thread called Virtual Threads (also known as Project Loom) which extends the traditional Thread class
- Virtual Threads are lightweight threads that are managed by the Java Virtual Machine (JVM) rather than the underlying operating system
- They are designed to handle a large number of concurrent tasks with minimal resource consumption
- Virtual Threads are particularly useful for I/O-bound operations, such as handling web requests, where threads spend a lot of time waiting for external resources
- By using Virtual Threads, developers can create applications that are more scalable and responsive, as they can handle many more concurrent tasks without the] overhead associated with traditional threads
- Virtual Threads can be created using the `Thread.ofVirtual().start()` method, and they

- can be used in the same way as traditional threads, including support for synchronization and thread-local variables
- Overall, Virtual Threads represent a significant advancement in Java's concurrency model, enabling developers to build high-performance applications with greater ease and efficiency.
- Example of creating a Virtual Thread:

```java
Thread virtualThread = Thread.ofVirtual().start(() -> {
    // Task to be executed in the virtual thread
    System.out.println("Hello from Virtual Thread!");
});
virtualThread.join(); // Wait for the virtual thread to finish

Thread.Builder - is an interface, which provides implementation for creating both platform and virtual threads. It has methods like start(), inheritInheritableThreadLocals(), name(), etc.
- Thread.ofPlatform() - static method that returns a Thread.Builder for creating platform threads.
- Thread.ofVirtual() - static method that returns a Thread.Builder for creating virtual threads.


Platform threads are limited (will give error if we go beyond 10 or 50k)
Virtual threads are lightweight and can be created in large numbers (will work even if we give 1M)