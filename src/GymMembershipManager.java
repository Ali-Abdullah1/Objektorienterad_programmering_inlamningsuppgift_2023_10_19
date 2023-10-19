import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GymMembershipManager {
    private final String gymMembershipFile = "src/GymMedlemskap";

    private String payingMembersFile = "src/BetalandeKunder.txt";
    private LocalDate currentDate;

    public GymMembershipManager() {
        this.currentDate = LocalDate.now();
    }

    public String getGymMembershipFile() {
        return gymMembershipFile;
    }

    public String getPayingMembersFile() {
        return payingMembersFile;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setPayingMembersFile(String payingMembersFile) {
        this.payingMembersFile = payingMembersFile;
    }

    public boolean findMember(String input) {
        boolean found = false;
        try (Scanner fileScanner = new Scanner(new File(gymMembershipFile))) {
            CustomFileHandler fileHandler = new CustomFileHandler(payingMembersFile);
            while (fileScanner.hasNextLine()) {
                String member = fileScanner.nextLine();
                String dateString = fileScanner.nextLine();
                String[] data = member.split(", ");
                if (data[0].equals(input) || data[1].equalsIgnoreCase(input)) {
                    found = true;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    LocalDate lastYear = currentDate.minusYears(1);
                    if (date.isAfter(lastYear)) {
                        System.out.println("Årsavgiften betalades för mindre än ett år sedan");
                        fileHandler.savePayingCustomerToFile(member, currentDate.toString());
                    } else {
                        System.out.println("Årsavgiften betalades för mer än ett år sedan");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }
}

