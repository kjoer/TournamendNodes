import java.util.Arrays;
import java.util.concurrent.LinkedTransferQueue;

public class Main {

    public static String deleteChars(String uebergebenString, char charToDelete) {
        String m = "";
        for (int i = 0; i < uebergebenString.length(); i++) {
            if (uebergebenString.charAt(i) != charToDelete) {
                m += uebergebenString.charAt(i);
            }
        }
        return m;
    }

    public static int[] zusammengefuegterSortedArray(int[] array1, int[] array2) {
        int[] helperArray = new int[array1.length + array2.length];
        for (int i = 0; i < helperArray.length; i++) {
            helperArray[i] = array1[i];
        }
        for (int d = array1.length + 1; d < helperArray.length; d++) {
            helperArray[d]=array2[d];
        }
        Arrays.sort(helperArray);
        return helperArray;
    }

    public static void main(String[] args) {
        System.out.println(deleteChars("IchTesteGerne", 'e'));

    }
}