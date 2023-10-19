import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileHandler {
    private String payingCustomersFile;

    public CustomFileHandler(String payingCustomersFile) {
        this.payingCustomersFile = payingCustomersFile;
    }

    public void savePayingCustomerToFile(String customerData, String date) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(payingCustomersFile, true))) {
            bufferedWriter.write(customerData + ", " + date + "\n");
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
