package ocp.threads;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduleAtFixedRate {

    Lock lock = new ReentrantLock();

    private int count = 0;

    public void multiThreadWithTimer() {
        ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
        Runnable task = this::executeMultiThreadIncrementerSync;
        Runnable task2 = this::executeMultiThreadIncrementerLock;
        Runnable task3 = this::executeMultiThreadIncrementerTryLock;
        service2.schedule(task, 0, TimeUnit.SECONDS);
        service2.schedule(task2, 5, TimeUnit.SECONDS);
        service2.schedule(task3, 10, TimeUnit.SECONDS);
        Runnable task4 = () -> System.out.println("test test test");
        service2.scheduleAtFixedRate(task4, 15, 1, TimeUnit.SECONDS);
    }

    private void incrementAndReportSync() {
        synchronized (this) {
            System.out.println((++count) + " ");
        }
    }

    private void incrementAndReportLock() {
        try {
            lock.lock();
            if(count == 30) {
                System.out.println("count 30 waiting for 3 sec");
                TimeUnit.MILLISECONDS.sleep(3000);
            }
            System.out.println((++count) + " ");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void incrementAndReportTryLock() {
        if(lock.tryLock()) {
            try {
                System.out.println((++count) + " ");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock!");
        }
    }

    public void executeMultiThreadIncrementerSync() {
        ExecutorService service = Executors.newFixedThreadPool(15);
         try {
             for(int i = 0; i < 20; i++) {
                 service.submit(this::incrementAndReportSync);
             }
         } finally {
             service.shutdown();
         }
    }

    public void executeMultiThreadIncrementerLock() {
        ExecutorService service = Executors.newFixedThreadPool(15);
        try {
            for(int i = 0; i < 20; i++) {
                service.submit(this::incrementAndReportLock);
            }
        } finally {
            service.shutdown();
        }
    }

    public void executeMultiThreadIncrementerTryLock() {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            for(int i = 0; i < 20; i++) {
                service.submit(this::incrementAndReportTryLock);
            }
        } finally {
            service.shutdown();
        }
    }
}
