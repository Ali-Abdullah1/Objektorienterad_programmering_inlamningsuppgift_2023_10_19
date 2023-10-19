import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GymMembershipManagerTest {
   /* @AfterEach
    public void cleanUp() {
        try {
            Files.write(Paths.get("src/TestPayingMembers.txt"), new byte[0]);
            Files.write(Paths.get("src/TestOutput.txt"), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void testFindMemberByNumber() {

        GymMembershipManager membershipManager = new GymMembershipManager();
        membershipManager.setPayingMembersFile("src/TestPayingMembers.txt");

        boolean isMemberFoundNumber = membershipManager.findMember("8204021234");
        assertTrue(isMemberFoundNumber);

        boolean isMemberFoundNumber2 = membershipManager.findMember("770123sesa");
        assertFalse(isMemberFoundNumber2);


    }
    @Test
    public void testFindMembersByName() {

        GymMembershipManager membershipManager = new GymMembershipManager();
        membershipManager.setPayingMembersFile("src/TestPayingMembers.txt");

        boolean isMemberFoundName = membershipManager.findMember("Chamade Coriola");
        assertTrue(isMemberFoundName);

        boolean isMemberFoundName2 = membershipManager.findMember("Görna Persson");
        assertFalse(isMemberFoundName2);

    }

    @Test
    public void testSavePayingCustomerToFile() throws Exception {
        CustomFileHandler fileHandler = new CustomFileHandler("src/TestOutput.txt");

        String customerData = "7911064434, Anders Petterson";
        String date = "2023-10-01";
        fileHandler.savePayingCustomerToFile(customerData, date);
        String fileContent = Files.readString(Paths.get("TestOutput.txt"));
        assertTrue(fileContent.contains("7911064434, Anders Petterson, 2023-10-01"));

        String customerData2 = "8307015678, Emma Johnson";
        String date2 = "2023-10-01";
        fileHandler.savePayingCustomerToFile(customerData2, date2);
        String fileContent2 = Files.readString(Paths.get("TestOutput.txt"));
        assertTrue(fileContent2.contains("8307015678, Emma Johnson, 2023-10-01"));

        String customerData3 = "8307015678, Ali Abdullah";
        String date3 = "2023-10-01";
        fileHandler.savePayingCustomerToFile(customerData3, date3);
        String fileContent3 = Files.readString(Paths.get("TestOutput.txt"));
        assertFalse(fileContent3.contains("8307012378, Emma Johnson, 2023-12-01"));

        }
}
