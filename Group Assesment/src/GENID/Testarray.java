import java.util.Scanner;

public class Testarray {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter first number- ");
        String a = s.nextLine();
        String[] vowels = { "A", "I", "E", "O", "U", "B009" };

        for (String elements : vowels) {
            if (a.equals(elements)) {
                System.out.println("a found in the vowels list.");
            }
        }
    }
}
