package ocp.threads.deadlock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DeadLock {
    static class Food {
    }

    static class Water {
    }

    ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public class Fox {
        private final String name;

        public Fox(String name) {
            this.name = name;
        }

        public void eatAndDrink(Food food, Water water) {
            synchronized (food) {
                System.out.println(this.name + " got food!");
                move();

//                uncomment to deadlock

//                synchronized (water) {
                System.out.println(this.name + " got water");
//                }
            }
        }

        public void drinkAndEat(Food food, Water water) {
            synchronized (water) {
                System.out.println(this.name + " got water!");
                move();
                synchronized (food) {
                    System.out.println(this.name + " got food");
                }
            }
        }
    }

    public void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void runDeadLock() {
        Fox foxy = new Fox("Foxy");
        Fox tails = new Fox("Tails");
        Water water = new Water();
        Food food = new Food();

        try {
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            service.shutdown();
        }
    }
}
