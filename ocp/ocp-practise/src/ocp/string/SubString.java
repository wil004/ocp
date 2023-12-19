package ocp.string;

public class SubString {

    public String capitalizeString(String word, int index1, int index2) {
        word = word.substring(index1, index2).toUpperCase() + word.substring(index2).toLowerCase();
        return word;
    }
}
