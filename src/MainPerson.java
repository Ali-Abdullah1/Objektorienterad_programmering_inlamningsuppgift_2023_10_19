import java.io.IOException;
import java.util.Scanner;

public class MainPerson {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ange fullständigt namn eller personnummer: ");
        String input = scan.nextLine();

        GymMembershipManager membershipManager = new GymMembershipManager();
        boolean found = membershipManager.findMember(input);
        if (!found) {
            System.out.println("Aldrig varit medlem och är obehörig");
        }
    }
}
