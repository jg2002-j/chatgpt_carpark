import java.io.IOException;
import java.time.LocalDateTime;

public class TestBackend {
    public static void main(String[] args) {
        try {
            // Initialize backend components
            CSVHandler csvHandler = new CSVHandler();

            // Test VRN Validation and Standardization
            String testVRN = "ab12 cde";
            if (AppUtil.validateVRN(testVRN)) {
                System.out.println("VRN is valid: " + testVRN);
                String standardizedVRN = AppUtil.standardizeVRN(testVRN);
                System.out.println("Standardized VRN: " + standardizedVRN);

                // Add a new record
                VehicleRecord newRecord = new VehicleRecord(
                        standardizedVRN,
                        LocalDateTime.now(),
                        null);
                csvHandler.addRecord(newRecord);
                System.out.println("Added new record: " + newRecord);

                // Simulate exiting the car park
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                csvHandler.updateRecord(standardizedVRN, LocalDateTime.now());
                System.out.println("Updated record with exit time.");
            } else {
                System.out.println("Invalid VRN: " + testVRN);
            }

            // Display all records in the CSV
            System.out.println("Current Records:");
            csvHandler.readRecords().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
