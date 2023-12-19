package ocp.loops;

public class Loops {

    public void whileLoop(int count) {
        int i = 1;
        while(count >= i) {
            System.out.println("This is iteration number: " + i++ + " of the while loop.");
        }

        do {
            System.out.println("The do while loop executes once even though the condition of the while loop is false i"
            + i++ + " is bigger than count" + count + " here!");
        } while (count >= i);
    }

    public int getClosestToZero(int[] numbers) {
        int closestToZero = Integer.MAX_VALUE;
        for(int number : numbers) {
            if (Math.abs(number) < Math.abs(closestToZero)) {
                closestToZero = number;
            } else if(Math.abs(closestToZero) == Math.abs(number)) {
                closestToZero = Math.abs(number);
            }
        }
        return closestToZero;
    }
}
