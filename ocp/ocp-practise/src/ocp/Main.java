package ocp;

import ocp.classes.AbstractClass;
import ocp.classes.SubClass;
import ocp.comparators.Human;
import ocp.loops.Loops;
import ocp.loops.Switch;
import ocp.threads.CyclicBarrierPractise;
import ocp.threads.ScheduleAtFixedRate;
import ocp.streams.PermutationGenerator;
import ocp.streams.StreamTest;
import ocp.string.SubString;
import ocp.threads.deadlock.DeadLock;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger("OCP_LOGGER");

    public static void main(String[] args) {
        System.out.println("Hello world!");

        chapter3();

        SubString substring = new SubString();
        System.out.println(substring.capitalizeString("this is a string", 0, 1));

        AbstractClass subClass = new SubClass();
        subClass.notAbstractMethod();

        compareWithComparator();

        StreamTest streamTest = new StreamTest();
        LOGGER.info(String.format("Smallest number = %d ", streamTest.findSmallest()));

        PermutationGenerator permutationGenerator = new PermutationGenerator();
        List<String> permutationsList = permutationGenerator.generate2("", "53A8ab");
        permutationsList.forEach(System.out::println);

        ScheduleAtFixedRate scheduleAtFixedRate = new ScheduleAtFixedRate();
        scheduleAtFixedRate.multiThreadWithTimer();
//
//        CyclicBarrierPractise cyclicBarrierPractise = new CyclicBarrierPractise();
//        cyclicBarrierPractise.performTask();
//
//        DeadLock deadLock = new DeadLock();
//        deadLock.runDeadLock();

    }

    public static void compareWithComparator() {
        Human human = new Human("Henk", 15);

        Human human2 = new Human("William", 27);

        Human bestHuman = new Human("Caca", 25);

        Human alsoBestHuman = new Human("Tyler", 1);

        Human startsWithT = new Human("T", 26);

        Human alsoStartsWithT = new Human("T", 25);

        List<Human> humanList = new ArrayList<>(List.of(human, human2, bestHuman, alsoBestHuman,
                startsWithT, alsoStartsWithT));

        // creates new list
        List<Human> sortedList = humanList.stream()
                .sorted(Comparator.comparing(Human::getName)
                        .thenComparing(Human::getAge))
                .toList();

        System.out.println("human-list unchanged");
        for (int i = 0; i < 6; i++) {
            System.out.print(humanList.get(i));
            System.out.println(", age = " + humanList.get(i).getAge());
        }

        System.out.println("sorted human list");
        for (int i = 0; i < 6; i++) {
            System.out.print(sortedList.get(i) + " sorted ");
            System.out.println(", age = " + sortedList.get(i).getAge());
        }
        System.out.println("human list changed");
        // changes humanList
        humanList.sort(Comparator.comparing(Human::getName).thenComparingInt(Human::getAge));

        for (int i = 0; i < 6; i++) {
            System.out.print(humanList.get(i));
            System.out.println(", age = " + humanList.get(i).getAge());
        }
    }

    public static void chapter3() {
        Switch aSwitch = new Switch();
        aSwitch.printDayOfWeek(5);
        aSwitch.printSeason(7);

        Loops whileLoop = new Loops();
        whileLoop.whileLoop(10);
        int closestToZero = whileLoop.getClosestToZero(new int[]{4, 8, -5, -3, -2, 5, 9, 22, 2, -1});
        System.out.println(closestToZero);
    }

}