# 🚦🧵 Summary: Concurrency and Parallelism in Java 🧵🚦

## 📑 Table of Contents

1. [🧠 Fundamental Concepts](#-fundamental-concepts)
   - [🔄 Concurrency vs. Parallelism](#-concurrency-vs-parallelism)
   - [⚠️ Race conditions, deadlocks, starvation, and livelocks](#-race-conditions-deadlocks-starvation-and-livelocks)
   - [🛡️ Immutability and thread-safety](#-immutability-and-thread-safety)
2. [🏗️ Classic APIs](#-classic-apis)
   - [🧵 Thread, Runnable, synchronized](#-thread-runnable-synchronized)
   - [⏳ wait(), notify(), notifyAll()](#-wait-notify-notifyall)
   - [🛠️ Manual thread management](#-manual-thread-management)
3. [🚀 Modern APIs](#-modern-apis)
   - [🗂️ ExecutorService, ScheduledExecutorService, ThreadPoolExecutor](#-executorservice-scheduledexecutorservice-threadpoolexecutor)
   - [📦 Future, Callable](#-future-callable)
   - [🗃️ Concurrent collections](#-concurrent-collections)
   - [🔒 Locks](#-locks)
   - [🧮 Atomic variables](#-atomic-variables)
   - [⏱️ CountDownLatch, CyclicBarrier, Semaphore, Phaser](#-countdownlatch-cyclicbarrier-semaphore-phaser)
4. [🌊 Java 8+ and Functional Programming](#-java-8-and-functional-programming)
   - [🏞️ Parallel streams](#-parallel-streams)
   - [🤝 CompletableFuture and async programming](#-completablefuture-and-async-programming)
5. [🛠️ Practices and Tools](#-practices-and-tools)
6. [✅ Best Practices](#-best-practices)
7. [✨ Extras](#-extras)
8. [🏋️ Hackerrank-style Exercises](#-hackerrank-style-exercises)
9. [⬅️ Back](#-back)

---

## 1. 🧠 Fundamental Concepts

> **Theory & Purpose:**
> This section introduces the foundational ideas behind concurrency and parallelism in Java. Understanding these concepts is crucial for designing efficient, responsive, and scalable applications that can handle multiple tasks at once or leverage multi-core processors for better performance.

### 🔄 Concurrency vs. Parallelism

> **Theory & Purpose:**
> Concurrency is about dealing with lots of things at once (structuring code to handle multiple tasks), while parallelism is about doing lots of things at once (executing tasks simultaneously). Both are essential for maximizing resource usage and application responsiveness.

#### Example 1: Simple Thread (Concurrency)
```java
public class Example1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Thread 1 running"));
        Thread t2 = new Thread(() -> System.out.println("Thread 2 running"));
        t1.start();
        t2.start();
    }
}
// Output: Thread 1 running\nThread 2 running (order may vary)
```
**🧑‍🍳 Analogy:** Imagine two chefs (threads) working in the same kitchen. They take turns using the stove (CPU), so sometimes one is cooking while the other waits. This is concurrency: both are making progress, but not always at the same time.

#### Example 2: Parallelism with parallelStream
```java
import java.util.stream.IntStream;
public class Example2 {
    public static void main(String[] args) {
        IntStream.range(1, 5).parallel().forEach(System.out::println);
    }
}
// Output: 1\n2\n3\n4 (order may vary)
```
**🏭 Analogy:** Think of a factory with four workers (CPU cores), each assembling a product at the same time. All tasks are happening simultaneously—this is parallelism.

#### Example 3: Runnable with Multiple Threads
```java
public class Example3 {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println(Thread.currentThread().getName() + " is running");
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}
// Output: Thread-0 is running\nThread-1 is running\nThread-2 is running (order may vary)
```
**🎤 Analogy:** Imagine three singers (threads) each with their own microphone. They can all sing their part independently, possibly at the same time or in quick succession.

---

### ⚠️ Race conditions, deadlocks, starvation, and livelocks

> **Theory & Purpose:**
> These are common problems in concurrent programming. Understanding them helps developers write safer, more reliable code by avoiding bugs that are hard to detect and reproduce.

- **Race Condition:** When two or more threads access shared data and try to change it at the same time.
- **Deadlock:** Two or more threads are blocked forever, each waiting for the other.
- **Starvation:** A thread is perpetually denied access to resources.
- **Livelock:** Threads are not blocked but keep changing state in response to each other without making progress.

#### Example 1: Race Condition
```java
class Counter {
    int count = 0;
    void increment() { count++; }
}
public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread t1 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        Thread t2 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println(c.count); // Output may be < 2000
    }
}
```
**🏦 Analogy:** Imagine two bank tellers (threads) updating the same account balance at the same time without coordination. Sometimes, one update overwrites the other, so the final balance is wrong. This is a race condition.

#### Example 2: Deadlock
```java
public class DeadlockDemo {
    static final Object Lock1 = new Object();
    static final Object Lock2 = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (Lock1) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("Thread 1 acquired both locks");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (Lock2) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (Lock1) {
                    System.out.println("Thread 2 acquired both locks");
                }
            }
        });
        t1.start(); t2.start();
    }
}
// Output: (May deadlock and print nothing)
```
**🔒 Analogy:** Two people (threads) are each holding a key to a door (resource) the other needs to get through. They can't proceed until they both have the other's key, which will never happen—this is deadlock.

#### Example 3: Starvation
```java
public class StarvationDemo {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread lowPriority = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Low priority thread running");
            }
        });
        Thread highPriority = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    // Keeps acquiring the lock
                }
            }
        });
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.start();
        lowPriority.start();
    }
}
// Output: Low priority thread may never run
```
**🚫 Analogy:** Imagine a bathroom (resource) with two stalls: one always has a line (high-priority thread), and the other is empty (low-priority thread). The person in the empty stall might never get a chance to use the bathroom because the other stall is always occupied.

---

### 🛡️ Immutability and thread-safety

> **Theory & Purpose:**
> Immutability and thread-safety are key strategies for preventing concurrency issues. Immutability ensures objects cannot be changed after creation, while thread-safety ensures correct behavior when multiple threads access shared data.

- **Immutability:** Objects that cannot change state after creation are naturally thread-safe.
- **Thread-safety:** Achieved via synchronization, concurrent collections, or atomic variables.

#### Example 1: Immutable Class
```java
final class Person {
    private final String name;
    public Person(String name) { this.name = name; }
    public String getName() { return name; }
}
// Usage: Person p = new Person("Alice"); System.out.println(p.getName());
// Output: Alice
```
**🛡️ Analogy:** An immutable object is like a statue: once it's carved (created), it can't be changed. You can look at it or take a picture with it, but you can't alter its form.

#### Example 2: Synchronized Method
```java
class SafeCounter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public int getCount() { return count; }
}
// Usage: SafeCounter c = new SafeCounter(); c.increment();
// Output: 1
```
**🔒 Analogy:** A synchronized method is like a bathroom with a lock: only one person (thread) can use it at a time. If another person tries to enter, they have to wait outside until the first person is done.

#### Example 3: Using AtomicInteger
```java
import java.util.concurrent.atomic.AtomicInteger;
public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ai.incrementAndGet();
        System.out.println(ai.get());
    }
}
// Output: 1
```
**⚛️ Analogy:** Using `AtomicInteger` is like having a thread-safe piggy bank: you can add or remove coins (increment or decrement) without worrying about someone else stealing a coin while you're not looking.

---

## 2. 🏗️ Classic APIs

> **Theory & Purpose:**
> Classic Java concurrency APIs provide the basic building blocks for multithreaded programming, such as creating threads, synchronizing access, and coordinating between threads.

### 🧵 Thread, Runnable, synchronized

#### Example 1: Extending Thread
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("MyThread running");
    }
}
// Usage: new MyThread().start();
// Output: MyThread running
```
**🧵 Analogy:** Extending the Thread class is like creating a custom light switch: you can turn it on (start the thread) and it will do its predefined job (run the code in `run()` method).

#### Example 2: Implementing Runnable
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("MyRunnable running");
    }
}
// Usage: new Thread(new MyRunnable()).start();
// Output: MyRunnable running
```
**🔌 Analogy:** Implementing Runnable is like providing a plug-in device: you provide the functionality (in `run()` method), and it can be used anywhere (in any thread) by plugging it in.

#### Example 3: Synchronized Block
```java
class SyncBlock {
    private int value = 0;
    public void increment() {
        synchronized(this) {
            value++;
        }
    }
}
// Usage: SyncBlock sb = new SyncBlock(); sb.increment();
// Output: 1
```
**🔒 Analogy:** A synchronized block is like a secure vault within a bank: only one person (thread) can access the valuables (code inside the block) at a time, ensuring safety from theft (race conditions).

---

### ⏳ wait(), notify(), notifyAll()

#### Example 1: wait/notify
```java
class WaitNotifyDemo {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread waiting");
                    lock.wait();
                    System.out.println("Thread resumed");
                } catch (InterruptedException e) {}
            }
        });
        t1.start();
        Thread.sleep(100);
        synchronized (lock) {
            lock.notify();
        }
    }
}
// Output: Thread waiting\nThread resumed
```
**⏳ Analogy:** Using wait/notify is like a student (thread) waiting for a teacher (notify) to continue a lesson. The student puts up their hand (waits) and the teacher acknowledges (notifies) when it's time to continue.

#### Example 2: notifyAll
```java
class NotifyAllDemo {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " resumed");
                } catch (InterruptedException e) {}
            }
        };
        new Thread(r).start();
        new Thread(r).start();
        Thread.sleep(100);
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
// Output: Thread-0 resumed\nThread-1 resumed (order may vary)
```
**📢 Analogy:** `notifyAll` is like a teacher announcing that class is over. All students (threads) are notified at once and can leave the classroom (resume execution).

#### Example 3: Producer-Consumer
```java
import java.util.LinkedList;
class ProducerConsumer {
    public static void main(String[] args) {
        LinkedList<Integer> buffer = new LinkedList<>();
        Object lock = new Object();
        Thread producer = new Thread(() -> {
            synchronized (lock) {
                buffer.add(1);
                lock.notify();
            }
        });
        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                while (buffer.isEmpty()) {
                    try { lock.wait(); } catch (InterruptedException e) {}
                }
                System.out.println("Consumed: " + buffer.remove());
            }
        });
        consumer.start();
        producer.start();
    }
}
// Output: Consumed: 1
```
**🍽️ Analogy:** The producer-consumer problem is like a restaurant kitchen (producer) and dining area (consumer). The kitchen prepares food and notifies the dining area when it's ready. The dining area waits for the notification to pick up the food.

---

### 🛠️ Manual thread management

#### Example 1: Creating and joining threads
```java
public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> System.out.println("Thread running"));
        t.start();
        t.join();
        System.out.println("Main finished");
    }
}
// Output: Thread running\nMain finished
```
**🔗 Analogy:** Joining a thread is like waiting for a friend to finish their call before you start your conversation. You don't want to interrupt, so you patiently wait (join) until they're done.

#### Example 2: Interrupting a thread
```java
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // running
            }
            System.out.println("Thread interrupted");
        });
        t.start();
        Thread.sleep(100);
        t.interrupt();
    }
}
// Output: Thread interrupted
```
**🚫 Analogy:** Interrupting a thread is like tapping someone on the shoulder to get their attention. The tapped person (thread) will stop what they're doing and respond to the tap (interruption).

#### Example 3: Daemon thread
```java
public class DaemonExample {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {}
        });
        t.setDaemon(true);
        t.start();
        System.out.println("Main finished");
    }
}
// Output: Main finished
```
**👻 Analogy:** A daemon thread is like a ghost that only appears when there's someone alive (user thread) around. Once the last person leaves, the ghost disappears.

---

## 3. 🚀 Modern APIs

> **Theory & Purpose:**
> Modern Java concurrency APIs offer higher-level abstractions for managing threads, tasks, and synchronization, making concurrent programming safer and more productive.

### 🗂️ ExecutorService, ScheduledExecutorService, ThreadPoolExecutor

#### Example 1: ExecutorService
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() -> System.out.println("Task 2"));
        executor.shutdown();
    }
}
// Output: Task 1\nTask 2 (order may vary)
```
**🛠️ Analogy:** Using ExecutorService is like hiring a taxi service. You request a ride (submit a task), and the taxi company (ExecutorService) sends a car (thread) to pick you up. You don't need to know how many cars they have or how they manage them.

#### Example 2: ScheduledExecutorService
```java
import java.util.concurrent.*;
public class ScheduledDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> System.out.println("Scheduled task"), 1, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
// Output: Scheduled task (after 1 second)
```
**⏰ Analogy:** ScheduledExecutorService is like setting an alarm clock. You set the time (schedule), and when the time is up, the alarm rings (task is executed).

#### Example 3: ThreadPoolExecutor
```java
import java.util.concurrent.*;
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        pool.execute(() -> System.out.println("ThreadPool task"));
        pool.shutdown();
    }
}
// Output: ThreadPool task
```
**🏭 Analogy:** ThreadPoolExecutor is like a factory assembly line. You have a fixed number of workers (threads) and you send tasks (products) down the line. Each worker takes a task, processes it, and then takes another one.

---

### 📦 Future, Callable

#### Example 1: Callable with Future
```java
import java.util.concurrent.*;
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> 42);
        System.out.println(future.get());
        executor.shutdown();
    }
}
// Output: 42
```
**🔮 Analogy:** Using Callable with Future is like placing a bet: you give your money (task) to the bookmaker (executor), and they promise to pay you back with winnings (result) later.

#### Example 2: Future isDone
```java
import java.util.concurrent.*;
public class FutureIsDoneDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> "Done");
        while (!future.isDone()) {}
        System.out.println(future.get());
        executor.shutdown();
    }
}
// Output: Done
```
**⏳ Analogy:** Checking if a Future is done is like waiting for a pot of water to boil. You don't keep looking inside; you just wait until you hear the whistle (completion notification).

#### Example 3: Canceling a Future
```java
import java.util.concurrent.*;
public class CancelFutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            while (true) {}
        });
        Thread.sleep(100);
        future.cancel(true);
        System.out.println(future.isCancelled());
        executor.shutdown();
    }
}
// Output: true
```
**🚫 Analogy:** Canceling a Future is like canceling a pizza order. You call the restaurant and ask them to stop making your pizza (task). If they haven't started yet, nothing happens. If they have, they toss the pizza (task) and stop working on it.

---

### 🗃️ Concurrent collections

#### Example 1: ConcurrentHashMap
```java
import java.util.concurrent.*;
public class ConcurrentMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        System.out.println(map.get("A"));
    }
}
// Output: 1
```
**🗺️ Analogy:** A ConcurrentHashMap is like a multi-story parking garage. Cars (data) can enter and exit simultaneously on different floors (segments) without interfering with each other.

#### Example 2: CopyOnWriteArrayList
```java
import java.util.concurrent.CopyOnWriteArrayList;
public class CopyOnWriteDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Hello");
        System.out.println(list.get(0));
    }
}
// Output: Hello
```
**📚 Analogy:** A CopyOnWriteArrayList is like a library book: when you borrow it (read), the library makes a copy for you. You can read it as much as you want, and the original book remains unchanged.

#### Example 3: ConcurrentLinkedQueue
```java
import java.util.concurrent.ConcurrentLinkedQueue;
public class ConcurrentQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(10);
        System.out.println(queue.poll());
    }
}
// Output: 10
```
**Queue Analogy:** A ConcurrentLinkedQueue is like a ticket line at a concert. People (data) join the line (queue) and leave from the front when their turn comes, all without any confusion or mix-up.

---

### 🔒 Locks

#### Example 1: ReentrantLock
```java
import java.util.concurrent.locks.ReentrantLock;
public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("Locked section");
        } finally {
            lock.unlock();
        }
    }
}
// Output: Locked section
```
**🔑 Analogy:** A ReentrantLock is like a hotel room key: you can lock (secure) the room (code section) when you're inside, and unlock (exit) it when you're done. If you leave and come back, you can use the same key to enter again.

#### Example 2: ReadWriteLock
```java
import java.util.concurrent.locks.*;
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        rwLock.readLock().lock();
        System.out.println("Read lock acquired");
        rwLock.readLock().unlock();
    }
}
// Output: Read lock acquired
```
**📖 Analogy:** A ReadWriteLock is like a public library: many people can read books (data) at the same time, but when someone wants to write or update a book, they get exclusive access until they're done.

#### Example 3: Condition
```java
import java.util.concurrent.locks.*;
public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            condition.signalAll();
            System.out.println("Condition signaled");
        } finally {
            lock.unlock();
        }
    }
}
// Output: Condition signaled
```
**📢 Analogy:** Using a Condition is like a teacher signaling all students to start working on their assignment. All students (threads) respond to the signal and begin their task.

---

### 🧮 Atomic variables

#### Example 1: AtomicInteger
```java
import java.util.concurrent.atomic.AtomicInteger;
public class AtomicIntDemo {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ai.incrementAndGet();
        System.out.println(ai.get());
    }
}
// Output: 1
```
**⚛️ Analogy:** An AtomicInteger is like a thread-safe counter at a ticket booth. No matter how many people are buying tickets at the same time, the counter always shows the correct number of tickets sold.

#### Example 2: AtomicReference
```java
import java.util.concurrent.atomic.AtomicReference;
public class AtomicRefDemo {
    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<>("A");
        ref.set("B");
        System.out.println(ref.get());
    }
}
// Output: B
```
**🔗 Analogy:** An AtomicReference is like a light switch that can be turned on or off (referenced) by multiple people at the same time. Everyone can see the current state of the switch, and changing the state is instantaneous and thread-safe.

#### Example 3: AtomicBoolean
```java
import java.util.concurrent.atomic.AtomicBoolean;
public class AtomicBoolDemo {
    public static void main(String[] args) {
        AtomicBoolean ab = new AtomicBoolean(false);
        ab.set(true);
        System.out.println(ab.get());
    }
}
// Output: true
```
**✅ Analogy:** An AtomicBoolean is like a shared checklist item that multiple people can check or uncheck. No matter how many people are accessing the checklist at the same time, each check or uncheck action is registered immediately and safely.

---

### ⏱️ CountDownLatch, CyclicBarrier, Semaphore, Phaser

#### Example 1: CountDownLatch
```java
import java.util.concurrent.CountDownLatch;
public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("Thread running");
            latch.countDown();
        }).start();
        latch.await();
        System.out.println("Main continues");
    }
}
// Output: Thread running\nMain continues
```
**🔔 Analogy:** A CountDownLatch is like a starting pistol at a race. The runners (threads) wait for the signal (latch countdown) to start running. Once the signal is given, all runners start simultaneously.

#### Example 2: CyclicBarrier
```java
import java.util.concurrent.CyclicBarrier;
public class BarrierDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("Barrier reached!"));
        new Thread(() -> {
            try { barrier.await(); } catch (Exception e) {}
        }).start();
        barrier.await();
    }
}
// Output: Barrier reached!
```
**🚧 Analogy:** A CyclicBarrier is like a construction crew that can't proceed until all members are present. Once everyone arrives, they get the go-ahead to start working.

#### Example 3: Semaphore
```java
import java.util.concurrent.Semaphore;
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);
        sem.acquire();
        System.out.println("Acquired semaphore");
        sem.release();
    }
}
// Output: Acquired semaphore
```
**🚦 Analogy:** A Semaphore is like a traffic light for threads. It controls the access (green light) to a particular resource, allowing a certain number of threads to use it simultaneously.

---

## 4. 🌊 Java 8+ and Functional Programming

> **Theory & Purpose:**
> Java 8 introduced functional programming features and new concurrency tools, enabling more expressive, concise, and parallelizable code for modern applications.

### 🏞️ Parallel streams

#### Example 1: Parallel processing
```java
import java.util.*;
public class ParallelStreamDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        list.parallelStream().forEach(System.out::println);
    }
}
// Output: 1\n2\n3\n4 (order may vary)
```
**🏭 Analogy:** Using parallel streams is like having multiple assembly lines in a factory. Each line works on a different part of the product simultaneously, speeding up the entire production process.

#### Example 2: Collecting results
```java
import java.util.*;
import java.util.stream.Collectors;
public class ParallelCollectDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<Integer> result = list.parallelStream().map(x -> x * 2).collect(Collectors.toList());
        System.out.println(result);
    }
}
// Output: [2, 4, 6, 8]
```
**📦 Analogy:** Collecting results from a parallel stream is like gathering all the finished products from different assembly lines into a single warehouse. Each product is complete and ready for delivery.

#### Example 3: Parallel sum
```java
import java.util.*;
public class ParallelSumDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = list.parallelStream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}
// Output: 10
```
**➕ Analogy:** Calculating a sum using parallel streams is like having multiple cashiers at a supermarket checkout. Each cashier processes a few customers (data chunks) simultaneously, making the checkout process faster.

---

### 🤝 CompletableFuture and async programming

#### Example 1: Simple CompletableFuture
```java
import java.util.concurrent.*;
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        System.out.println(future.get());
    }
}
// Output: Hello
```
**🔮 Analogy:** A CompletableFuture is like a magic box: you put a request in (supplyAsync), and it promises to deliver the result later. You can continue with other tasks and check the box (get) when you're ready for the result.

#### Example 2: Chaining CompletableFutures
```java
import java.util.concurrent.*;
public class ChainFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2)
            .thenApply(x -> x * 3);
        System.out.println(future.get());
    }
}
// Output: 6
```
**🔗 Analogy:** Chaining CompletableFutures is like a relay race: one runner (task) passes the baton (result) to the next runner in line. Each runner knows exactly what to do with the baton.

#### Example 3: Combining CompletableFutures
```java
import java.util.concurrent.*;
public class CombineFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture<Integer> combined = f1.thenCombine(f2, Integer::sum);
        System.out.println(combined.get());
    }
}
// Output: 5
```
**➕ Analogy:** Combining CompletableFutures is like merging two rivers: each river (future) flows independently, but when they meet, they create a stronger, combined flow (result).

---

## 5. 🛠️ Practices and Tools

> **Theory & Purpose:**
> This section covers practical techniques and tools for identifying, analyzing, and testing concurrency issues in real-world Java applications.

### 🕵️ Identifying and solving concurrency issues
- Use thread dumps, logging, and analysis tools to identify deadlocks, race conditions, and performance bottlenecks.

### 🧰 Analysis tools (VisualVM, JConsole, Thread dumps)
- VisualVM and JConsole are powerful tools for monitoring and analyzing Java applications in real time.

### 🧪 Concurrency testing
- Use frameworks like JUnit and tools like jcstress for concurrency testing.

---

## 6. ✅ Best Practices

> **Theory & Purpose:**
> Best practices help developers avoid common pitfalls and write robust, maintainable concurrent code.

---

## 7. ✨ Extras

> **Theory & Purpose:**
> Advanced topics and frameworks that extend Java’s concurrency capabilities, including memory model details and integration with popular frameworks.

---

## 🏋️ Hackerrank-style Exercises

1. Implement a thread-safe counter in Java.
2. Create a deadlock example and explain how to avoid it.
3. Write a program that demonstrates starvation.
4. Implement an immutable 2D point class.
5. Demonstrate the use of parallelStream to process a list.
6. Create a race condition example and fix it.
7. Implement a simple livelock.
8. Explain the difference between concurrency and parallelism with code.
9. Use `synchronized` to protect a shared resource.
10. Show how to use `AtomicInteger` for thread-safety.

---

**New Exercises:**

11. Simulate a restaurant where multiple waiters (threads) serve customers, but only one can access the kitchen at a time.
12. Write a program where two threads print numbers in order (one prints even, the other odd).
13. Implement a producer-consumer scenario using wait/notify.
14. Create a thread pool that processes a list of tasks and prints the result of each.
15. Use a `CountDownLatch` to make the main thread wait for three worker threads to finish before printing "All done!".

<details>
<summary>Answers (click to expand)</summary>

<!--
1. Use AtomicInteger or synchronized.
2. Two crossed locks, avoid by ordering lock acquisition.
3. Low-priority thread never runs.
4. Class with final fields and no setters.
5. list.parallelStream().forEach(...)
6. Shared variable without synchronization, fix with synchronized.
7. Two threads yielding resources to each other repeatedly.
8. Concurrency: interleaving, Parallelism: simultaneous execution.
9. synchronized(method or block).
10. AtomicInteger.incrementAndGet().
11. Use synchronized block for kitchen access.
12. Use wait/notify and a shared counter.
13. Shared buffer, producer adds, consumer removes, both use wait/notify.
14. Use ExecutorService and submit tasks.
15. Create CountDownLatch(3), each worker calls countDown(), main calls await().
-->

</details>

---

## ⬅️ [Back](../README.md)

---



