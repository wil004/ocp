package ocp.classes;

public abstract class AbstractClass {

    public abstract void abstractMethod();

    public void notAbstractMethod() {
        System.out.println("This method is not abstract!");
        abstractMethod();
    }
}
