package ocp.streams;

import java.util.Comparator;
import java.util.List;

public class StreamTest {

    private final List<Integer> integers = List.of( -3, -3, -4, 8, -5, -200, -15, 20, 21, 777, -4, 18, 5);

    public Integer findSmallest() {
        int smallestNumber = integers.stream().min(Comparator.comparingInt(Math::abs)).orElseThrow();

        if (integers.stream().anyMatch(i -> i == Math.abs(smallestNumber))) {
            return Math.abs(smallestNumber);
        } else {
            return smallestNumber;
        }
    }
}
