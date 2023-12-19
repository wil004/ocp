package ocp.streams;

import java.util.*;

public class PermutationGenerator {

    List<String> nameList = new ArrayList<>();

    public void generate(String str, String ans) {

        if (str.length() == 0) {
            nameList.add(ans);
            System.out.println(ans + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            String ros = str.substring(0, i) +
                    str.substring(i + 1);
            // Recursive call
            generate(ros, ans + ch);
        }
    }

    public List<String> generate2(String result, String toReduce) {
        List<String> permutations = new ArrayList<>();

        if(toReduce.length() == 0) {
            permutations.add(result);
            return permutations;
        }

        List<String> myList = new ArrayList<>(Arrays.asList(toReduce.split("")));

        return myList.stream().map(i -> generate2(result + i, myList.stream()
                .filter(j -> !Objects.equals(i, j))
                .reduce((k, l) -> k + l).orElse("")))
                .flatMap(Collection::stream)
                .toList();
    }

    public int listSize() {
        return nameList.size();
    }
}
