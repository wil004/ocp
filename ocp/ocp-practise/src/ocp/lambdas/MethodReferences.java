package ocp.lambdas;

public class MethodReferences {

    private String str = "Zoo";

    private String str2;

    public void checkStrings() {
        StringStart methodRef = str::startsWith;

        System.out.println(methodRef.beginningCheck("Z"));

    }

    public static void main(String[] args) {
        MethodReferences methodReferences = new MethodReferences();
        methodReferences.checkStrings();
    }
}
