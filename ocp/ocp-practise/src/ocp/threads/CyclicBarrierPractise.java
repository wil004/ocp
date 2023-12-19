package ocp.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Executors;

public class CyclicBarrierPractise {

    public void performTask() {
        var service = Executors.newFixedThreadPool(4);
        try {
            java.util.concurrent.CyclicBarrier c1 = new java.util.concurrent.CyclicBarrier(4);
            java.util.concurrent.CyclicBarrier c2 = new java.util.concurrent.CyclicBarrier(4,
                    () -> System.out.println("process finished!"));
            for(int i = 0; i < 4; i++) {
                service.submit(() -> performProcess(c1, c2));
            }

        } finally {
            service.shutdown();
        }
    }

    //aanroep 1
    // wacht
    // aanroep 2
    // aanroep 1 response
    // aanroep 3

    private void performProcess(java.util.concurrent.CyclicBarrier c1, java.util.concurrent.CyclicBarrier c2) {
        try {
            start();
            c1.await();
            during();
            c2.await();
            end();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() {
        System.out.println("starting process");
    }

    private void during() {
        System.out.println("during process");
    }

    private void end() {
        System.out.println("end process");
    }
}
